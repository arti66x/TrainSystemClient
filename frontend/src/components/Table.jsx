import React, { Component } from 'react'
import HttpService from '../services/HttpService';
import Parser2str from '../services/Parser2str';
import { PencilSquare } from "react-bootstrap-icons";
import { Trash } from "react-bootstrap-icons";

class Table extends Component {
    constructor(props) {
        super(props);
        this.state = {
            table_name: this.props.match.params.table,
            headers: [],
            entities: [],

            page: 1,
            inputPage: 1,
            rowsPerPage: 20,
            countPages: 1
        };
        this.addEntity = this.addEntity.bind(this);
        this.editEntity = this.editEntity.bind(this);
        this.deleteEntity = this.deleteEntity.bind(this);
        // this.parseTableData = this.parseTableData.bind(this);
        // this.parseTableData2 = this.parseTableData2.bind(this);

        this.getTableHeaders = this.getTableHeaders.bind(this);
        this.getTableBody = this.getTableBody.bind(this);
        this.getTablePagination = this.getTablePagination.bind(this);
        this.handlePageInput = this.handlePageInput.bind(this);
    }
    componentDidMount() {
        HttpService.getHeaders(this.state.table_name).then(data => {
            this.setState({ headers: data })
        });
        HttpService.getAll(this.state.table_name, this.state.page, this.state.rowsPerPage).then(data => {
            this.setState({ entities: data.entities, countPages: data.countPages })
            console.log(data)
        });
    }
    editEntity(id) {
        let strId
        if(typeof id === 'object')
            strId = Object.values(id).join('_')
        else strId = id
        this.props.history.push(`/${this.state.table_name}/${strId}`);
    }
    addEntity() {
        this.props.history.push(`/${this.state.table_name}/_add`)
    }
    deleteEntity(id) {
        let strId
        if(typeof id === 'object')
            strId = Object.values(id).join('_')
        else strId = id
        HttpService.deleteEntity(this.state.table_name, strId).then(data => {
            this.setState({
                entities: this.state.entities.filter(ent => ent['id'] !== id),
            })
        })
            .catch(function (error) {
                console.log(error)
                alert(error.response.data);
            })
    }
    getTableHeaders() {
        return (
            <tr>
                {this.state.headers.map(
                    h =>
                        <th key={h}>{h}</th>)
                }
                <th></th>
            </tr>
        )
    }


    

    getTableBody() {
        if (this.state.headers.includes('id'))
            return (
                this.state.entities.map(
                    ent =>
                        <tr key={ent[this.state.headers[0]]}>
                            {this.state.headers.map(
                                h =>
                                    <td key={h}>{Parser2str.parse(ent[h])}</td>)
                            }
                            <td>
                                <div className='btn-group'>
                                    <button onClick={() => this.editEntity(ent[this.state.headers[0]])} className="btn btn-outline-primary btn-sm">
                                        <PencilSquare />
                                    </button>
                                    <button onClick={() => this.deleteEntity(ent[this.state.headers[0]])} className="btn btn-outline-danger btn-sm">
                                        <Trash />
                                    </button>
                                </div>
                            </td>
                        </tr>
                )
            )
        else
            return (
                this.state.entities.map(
                    ent =>
                        <tr key={Object.values(ent['id']).join('_')}>
                            {this.state.headers.map(
                                h =>
                                    <td key={h}>{Parser2str.parse(ent[h])}</td>)
                            }
                            <td>
                                <div className='btn-group'>
                                    <button onClick={() => this.editEntity(ent['id'])} className="btn btn-outline-primary btn-sm">
                                        <PencilSquare />
                                    </button>
                                    <button onClick={() => this.deleteEntity(ent['id'])} className="btn btn-outline-danger btn-sm">
                                        <Trash />
                                    </button>
                                </div>
                            </td>
                        </tr>
                )
            )
    }

    changePage(newPage) {
        if (newPage <= 0) newPage = this.state.countPages
        if (newPage > this.state.countPages) newPage = 1

        HttpService.getAll(this.state.table_name, newPage, this.state.rowsPerPage).then(data => {
            this.setState({ entities: data.entities, countPages: data.countPages })
            this.setState({ page: newPage });
            this.setState({ inputPage: newPage });
        })

    };
    handlePageInput(e) {
        let _page = parseInt(e.target.value)
        if (_page && (_page > 0 && _page <= this.state.countPages)) {
            HttpService.getAll(this.state.table_name, _page, this.state.rowsPerPage).then(data => {
                this.setState({ entities: data.entities, countPages: data.countPages })
                this.setState({ page: _page });
                this.setState({ inputPage: _page });
                e.target.value = _page;
            })
        } else {
            e.target.value = this.state.page;
        }
    };

    getTablePagination() {
        return (
            <div className="input-group pagination">
                <div className="input-group-prepend">
                    <button className="btn btn-outline-secondary" onClick={() => this.changePage(this.state.page - 1)}>
                        <span>&laquo;</span>
                    </button>
                </div>
                <input type="text" maxLength="4" className="form-control input-num" placeholder="#" value={this.state.inputPage}
                    onChange={e => {
                        this.setState({
                            inputPage: e.target.value
                        })
                    }}

                    onKeyPress={event => {
                        if (event.key === 'Enter') {
                            this.handlePageInput(event)
                        }
                    }}
                />

                <div className="input-group-append">
                    <span className="input-group-text">/{this.state.countPages}</span>
                </div>
                <div className="input-group-append">
                    <button className="btn btn-outline-secondary" onClick={() => this.changePage(this.state.page + 1)}>
                        <span>&raquo;</span>
                    </button>
                </div>
            </div>

        )
    }



    render() {
        return (
            <div>
                <div className="table-nav-header">
                    <h2 className="text-center"> {this.state.table_name} </h2>
                    <button className="btn btn-success right-btn" onClick={this.addEntity}>Add</button>
                </div>
                <div className='table-overflow-x'>
                    <table className="table table-stripped table-bordered">
                        <thead>
                            <this.getTableHeaders />
                        </thead>
                        <tbody>
                            <this.getTableBody />
                        </tbody>
                    </table>
                </div>
                <this.getTablePagination />
            </div>
        );
    }
}
export default Table;