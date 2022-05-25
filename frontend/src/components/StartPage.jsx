import React, { Component } from 'react';
import HttpService from '../services/HttpService';

class StartPage extends Component {
    constructor(props) {
        super(props)
        this.state = {
            tables: []
        }
    }
    componentDidMount() {
        HttpService.getTablesList().then(data => {
            this.setState({ tables: data })
        });
    }

    render() {
        return (
            <div className='tables-list-container'>
                <h2 className="text-center "> Choose table </h2>
                <div className="list-group list-group-flush ">
                    {this.state.tables.map(
                        table =>
                            <a className="list-group-item list-group-item-action" key={table} href={`/crud/${table}`}>{table}</a>
                    )}
                </div>
            </div>
        );
    }
}

export default StartPage;