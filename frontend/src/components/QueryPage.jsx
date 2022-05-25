import React, { Component } from 'react';
import HttpService from '../services/HttpService';
import QueryForm from './QueryForm';

class StartPage extends Component {
    constructor(props) {
        super(props)
        this.state = {
            queryName: this.props.match.params.query,
            headers: [],
            entities: [],
            form: null,
            page: 1,
            inputPage: 1,
            rowsPerPage: 20,
            countPages: 1
        }
        this.updateTable = this.updateTable.bind(this)
        this.Table = this.Table.bind(this);
        this.TablePagination = this.TablePagination.bind(this);
        this.handlePageInput = this.handlePageInput.bind(this);
        this.changePage = this.changePage.bind(this);

    }

    updateTable(form) {
        this.setState({
            form:form
        })
        HttpService.getTableByQuery(this.state.queryName, form, this.state.page, this.state.rowsPerPage).then(data => {
            console.log(data)
            this.setState({ entities: data.entities, countPages: data.countPages })
            this.setState({ headers: Object.keys(data.entities[0]) })
        })
            .catch(function (error) {
                console.log(error)
                alert(error.response.data);
            })
    }

    Table() {
        return (
            <div className='table-overflow-x'>
                <table className="table table-stripped table-bordered">
                    <thead>
                        <tr>
                            {this.state.headers.map(
                                h =>
                                    <th key={h}>{h}</th>)
                            }
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.entities.map(
                                (ent,k) =>
                                    <tr key= {k}>
                                        {this.state.headers.map(
                                            h =>
                                                <td key={h}>{ent[h]}</td>)
                                        }
                                    </tr>
                            )
                        }
                    </tbody>
                </table>
            </div>
        )

    }




    changePage(newPage) {
        if (newPage <= 0) newPage = this.state.countPages
        if (newPage > this.state.countPages) newPage = 1

        HttpService.getTableByQuery(this.state.queryName, this.state.form, newPage, this.state.rowsPerPage).then(data => {
            console.log(data)
            this.setState({ entities: data.entities, countPages: data.countPages })
            this.setState({ headers: Object.keys(data.entities[0]) })
            this.setState({ page: newPage });
            this.setState({ inputPage: newPage });
        })

    };
    handlePageInput(e) {
        let newPage = parseInt(e.target.value)
        if (newPage && (newPage > 0 && newPage <= this.state.countPages)) {
            HttpService.getTableByQuery(this.state.queryName, this.state.form, newPage, this.state.rowsPerPage).then(data => {
                console.log(data)
                this.setState({ entities: data.entities, countPages: data.countPages })
                this.setState({ headers: Object.keys(data.entities[0]) })
                this.setState({ page: newPage });
                this.setState({ inputPage: newPage });
            })
        } else {
            e.target.value = this.state.page;
        }
    };

    TablePagination() {
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
                <QueryForm
                    queryName={this.state.queryName}
                    updateTable={this.updateTable}
                />
                <this.Table />
                {this.state.form &&
                <this.TablePagination />
                }
            </div>
        );
    }
}

export default StartPage;