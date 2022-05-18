import React, { Component } from 'react';
import HttpService from '../services/HttpService';
import Parser2str from '../services/Parser2str';
import Select from 'react-select'

class CreateForm extends Component {
    constructor(props) {
        super(props)
        this.state = {
            id: this.props.match.params.id,
            table_name: this.props.match.params.table,
            fieldsHeaders: [],
            fkIdList: [],
            scrollLists: {},
            entity: {},
        }
        this.inputHandler = this.inputHandler.bind(this);
        this.selectHandler = this.selectHandler.bind(this);
        this.saveEntity = this.saveEntity.bind(this);
        this.cancel = this.cancel.bind(this);
        this.getInputFields = this.getInputFields.bind(this);
        this.getListOrInputField = this.getListOrInputField.bind(this);
        this.fk2string = this.fk2string.bind(this);
    }
    componentDidMount() {
        HttpService.getFkId(this.state.table_name).then(list => {
            this.setState({ fkIdList: list })
        }).catch(function (error) {})
        HttpService.getHeaders(this.state.table_name).then(respFieldsHeaders => {
            this.setState({ fieldsHeaders: respFieldsHeaders })
            let newEntity = {}

            if (this.state.id === '_add') {
                for (let i = 0; i < respFieldsHeaders.length; i++) {
                    if (respFieldsHeaders[i] === "id") { continue; }
                    newEntity[respFieldsHeaders[i]] = '';
                }
                this.setState({
                    entity: newEntity
                })
            } else {
                HttpService.getObjById(this.state.table_name, this.state.id).then(data => {
                    HttpService.getForeignKeys(this.state.table_name).then(fks => {
                        this.setState({ scrollLists: fks })

                        for (let k of respFieldsHeaders) {
                            if (k === "id") { continue }
                            if (data[k] !== null) {
                                if (fks[k] === null) {
                                    newEntity[k] = data[k]['id']
                                } else
                                    newEntity[k] = data[k];
                            }
                            else
                                newEntity[k] = '';
                        }
                        this.setState({
                            entity: newEntity
                        })
                    })
                });
            }
            if (this.state.id === '_add')
                HttpService.getForeignKeys(this.state.table_name).then(fks => {
                    this.setState({ scrollLists: fks })
                })
        });
    }
    saveEntity = (e) => {
        e.preventDefault();

        let newEntity = {}
        for (let prop of this.state.fieldsHeaders) {

            if (prop === 'id') continue
            if (this.state.scrollLists[prop] === null) {
                if (typeof this.state.entity[prop] ==='string' && this.state.entity[prop].trim() === '')
                    newEntity[prop] = null;
                else
                    newEntity[prop] = { id: this.state.entity[prop] }
            }
            else if (typeof this.state.entity[prop] === 'object')
                if (this.state.entity[prop] !== null)
                    newEntity[prop] = { id: this.state.entity[prop]['id'] }
                else
                    newEntity[prop] = null
            else if (typeof this.state.entity[prop] === 'string') {
                if (this.state.entity[prop].trim() === '')
                    newEntity[prop] = null
                else
                     newEntity[prop] = this.state.entity[prop]
            }
            else
                newEntity[prop] = this.state.entity[prop]

        }
        if (this.state.id === '_add') {
            HttpService.createEntity(this.state.table_name, newEntity)
                .then(res => { this.props.history.push('/' + this.state.table_name) })
                .catch(function (error) {
                    console.log(error)
                    alert(error.response.data);
                })

        } else {
            HttpService.updateEntity(this.state.table_name, this.state.id, newEntity)
                .then(res => {
                    this.props.history.push('/' + this.state.table_name);
                })
                .catch(function (error) {
                    console.log(error)
                    alert(error.response.data);
                })
        }
    }

    cancel() {
        this.props.history.push('/' + this.state.table_name);
    }

    inputHandler = (event) => {
        let newEntity = this.state.entity
        newEntity[event.target.name] = event.target.value
        this.setState({
            entity: newEntity
        })
    }
    selectHandler(val, key) {
        if (val === '')
            val = null
        let newEntity = this.state.entity
        newEntity[key] = val
        this.setState({
            entity: newEntity
        })
    }
    getTitle() {
        if (this.state.id === '_add') {
            return <h3 className="text-center">Add {this.state.table_name}</h3>
        } else {
            return <h3 className="text-center">Update {this.state.table_name}</h3>
        }
    }

    fk2string(val) {
        if ((typeof val) !== 'object') {
            return val.toString();
        } else {
            return Object.values(val).join(' ')
        }
    }
    getListOrInputField(keyName) {
        if(this.state.fkIdList.includes(keyName)&&this.state.id !== '_add'){
            return (
                <input maxLength="40" name={keyName} className="form-control"
                    value={Parser2str.parse(this.state.entity[keyName])}
                    readOnly 
                    />)
        }
        else 
        if (this.state.scrollLists[keyName] === null) {
            return (
                <input maxLength="40" name={keyName} className="form-control"
                    value={this.state.entity[keyName]}
                    onChange={this.inputHandler}
                    placeholder='id' />)
        }
        else if (Object.keys(this.state.scrollLists).includes(keyName)) {
            let selectedKey = 0
            if (this.state.entity[keyName] !== null)
                for (let i = 0; i < this.state.scrollLists[keyName].length; i++) {
                    let listElem = this.state.scrollLists[keyName][i]
                    if (this.state.entity[keyName]['id'] && (this.state.entity[keyName]['id'] === listElem['id']))
                        selectedKey = i + 1
                    else if (JSON.stringify(listElem) === JSON.stringify(this.state.entity[keyName])) {
                        selectedKey = i + 1
                    }
                }
            let options = this.state.scrollLists[keyName].map((listElem, i) => (
                { value: listElem, label: Parser2str.parse(listElem) }
            ))
            options.unshift({ value: '', label: '-' })
            return (
                <Select maxMenuHeight={'160px'}
                    name={keyName}
                    options={options}
                    onChange={(e) => { this.selectHandler(e.value, keyName) }}
                    defaultValue={options[selectedKey]}
                    label={keyName}
                />
            )
        } else {
            return (
                <input maxLength="40" name={keyName} className="form-control"
                    value={this.state.entity[keyName]}
                    onChange={this.inputHandler} />)
        }
    }

    getInputFields() {
        return (
            Object.keys(this.state.entity).map((keyName, i) => (
                <div className="form-group" key={i}>
                    <label>{keyName}:</label>
                    {this.getListOrInputField(keyName)}
                </div>
            ))
        )
    }

    render() {
        return (
            <div>
                <div className="card col-md-6 offset-md-3 offset-md-3">
                    {this.getTitle()}
                    <div className="card-body">
                        <div className="form-group">
                            <label>ID: <b>{this.state.id === '_add' ? 'generated' : this.state.id}</b></label>
                        </div>
                        <this.getInputFields />
                        <div style={{ marginTop: "10px" }}>
                            <button className="btn btn-success" onClick={this.saveEntity}>Save</button>
                            <button className="btn btn-danger" onClick={this.cancel} style={{ marginLeft: "10px" }}>Cancel</button>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default CreateForm;