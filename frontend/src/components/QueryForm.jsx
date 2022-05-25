import React, { Component } from 'react';
import HttpService from '../services/HttpService';
import Parser2str from '../services/Parser2str';
import Select from 'react-select'

class QueryForm extends Component {
    constructor(props) {
        super(props)
        this.state = {
            queryName: this.props.queryName,
            fields: [],
            selectList: {},
            form: {},
        }
        this.select = this.select.bind(this)
        this.clear = this.clear.bind(this)
        this.inputHandler = this.inputHandler.bind(this)
        this.selectHandler = this.selectHandler.bind(this)
        this.getItemVal = this.getItemVal.bind(this)
        this.getInputFields = this.getInputFields.bind(this);
        this.getListOrInputField = this.getListOrInputField.bind(this);

    }
    componentDidMount() {
        HttpService.getQuerySelectList(this.state.queryName).then(list => {
            this.setState({ selectList: list })
        })
        HttpService.getQueryFormFields(this.state.queryName).then(data => {
            this.setState({ fields: data })
            let newForm = {}
            for (let f of data  ) {
                newForm[f] = '';
            }
            this.setState({ form: newForm })           
        })
    }
    select = (e) => {
        e.preventDefault()
        let newForm = {}
        for (let k in this.state.form){
            if(typeof this.state.form[k]==='string' && this.state.form[k].trim()==='')
                newForm[k] = null
            else
            newForm[k] = this.state.form[k]
        }
        this.props.updateTable(newForm)
    }
    clear() {
        let newForm = {}
        for (let f of this.state.fields) {
            newForm[f] = '';
        }
        this.setState({ form: newForm })
    }
    inputHandler = (event) => {
        let newForm = this.state.form
        newForm[event.target.name] = event.target.value
        this.setState({
            form: newForm
        })
        // console.log(newForm)
    }
    selectHandler(val, key) {
        let newForm = this.state.form
        newForm[key] = val
        this.setState({
            form: newForm
        })
        // console.log(newForm)

    }
    getItemVal(data) {
        if (typeof data === 'object')
            return data['id']
        else return data;
    }
    getListOrInputField(fieldName) {
        if (Object.keys(this.state.selectList).includes(fieldName)) {
            let options = this.state.selectList[fieldName].map((listElem, i) => (
                { value: this.getItemVal(listElem), label: Parser2str.parse(listElem) }
            ))
            options.unshift({ value: '', label: '-' })
            let selectedKey = 0
            for (let i = 0; i < options.length; i++) {
                if(options[i].value ===this.state.form[fieldName])
                    selectedKey = i
            }
            return (
                <Select maxMenuHeight={'160px'}
                    name={fieldName}
                    options={options}
                    value = {options[selectedKey]}
                    onChange={(e) => { this.selectHandler(e.value, fieldName) }}
                    label={fieldName}
                />
            )
        }
        else {
            return (
                <input maxLength="40" name={fieldName} className="form-control"
                    value={this.state.form[fieldName]}
                    onChange={this.inputHandler} 
                    />)
        }
    }
    getInputFields() {
        return (
            this.state.fields.map(f => (
                <div className="form-group" key={f}>
                    <label>{f}:</label>
                    {this.getListOrInputField(f)}
                </div>
            ))
        )
    }

    render() {
        return (
            <div>
                <div className="card col-md-6 offset-md-3 offset-md-3">
                    <div className="card-body">
                        <this.getInputFields />
                        <div style={{ marginTop: "10px" }}>
                            <button className="btn btn-success" onClick={this.select}>Select</button>
                            <button className="btn btn-danger" onClick={(e) => { this.clear()}} style={{ marginLeft: "10px" }}>Clear</button>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default QueryForm;