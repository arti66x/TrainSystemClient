import React, { Component } from 'react';
import HttpService from '../services/HttpService';
import { Navbar, Nav, NavDropdown, Container } from 'react-bootstrap'
    class Header extends Component {
    constructor(props) {
        super(props)
        this.state = {
            tables: [],
            queries: [],
            isTablesListExpanded: false
        }
    }
    componentDidMount() {
        HttpService.getTablesList().then(data => {
            this.setState({ tables: data })
        });
        HttpService.getQueriesList().then(data => {
            this.setState({ queries: data })
        });
    }
    expandOrCollapseTablesList = () => this.setState({ isTablesListExpanded: !this.state.isTablesListExpanded });


    render() {
        return (
            <header>

                <Navbar bg="dark" variant="dark">
                <Container>
                    <Navbar.Brand href="/">RZD-client</Navbar.Brand>
                    <Nav className="me-auto">
                        <NavDropdown title="Tables" className='shadow' id="basic-nav-dropdown">
                            {this.state.tables.length > 0 &&
                                 this.state.tables.map(
                                     table =>
                                        <NavDropdown.Item key={table} href={`/${table}`}>{table}</NavDropdown.Item>
                             )}
                        </NavDropdown>
                        <NavDropdown title="Queries" id="basic-nav-dropdown">
                            {this.state.queries.length > 0 &&
                                 this.state.queries.map(
                                     table =>
                                        <NavDropdown.Item key={table} href={`/${table}`}>{table}</NavDropdown.Item>
                             )}
                        </NavDropdown>
                    </Nav>
                    </Container>
                </Navbar>
            </header>
        );
    }
}

export default Header;