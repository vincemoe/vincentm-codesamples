import React, {Component} from 'react';
import {Container, Table, Button} from 'react-bootstrap';
import {Fetch} from 'react-request';
import './Home.css';
import config from 'react-global-configuration';

class Home extends Component {
    render() {
        return (
            <div className="TableContainer">
                <Container>
                    <h2>Recent Dispatches</h2>
                    <Fetch url={config.get('api_base_url')}>
                        {({fetching, failed, data}) => {
                            if (fetching) {
                                return <div>Loading data...</div>;
                            }

                            if (failed) {
                                return <div>The request did not succeed.</div>;
                            }

                            if (data) {
                                return (
                                    <div>
                                        <Table striped bordered hover>
                                            <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>UID</th>
                                                <th>Dispatch Text</th>
                                                <th>Address</th>
                                                <th>Timestamp</th>
                                                <th></th>
                                            </tr>
                                            </thead>
                                            <tbody>

                                            {data.map((dispatch, index) => (
                                                <tr key={dispatch.text_uid}>
                                                    <td>{dispatch.id}</td>
                                                    <td>{dispatch.text_uid}</td>
                                                    <td>{dispatch.dispatch_text}</td>
                                                    <td>{dispatch.address}</td>
                                                    <td>{dispatch.timestamp}</td>
                                                    <td><Button variant="primary" href={"/dispatch/"+dispatch.text_uid}>View</Button></td>
                                                </tr>
                                            ))}
                                            </tbody>
                                        </Table>
                                    </div>
                                );
                            }
                            return null;
                        }}
                    </Fetch>
                </Container>
            </div>
        );
    }
}

export default Home;
