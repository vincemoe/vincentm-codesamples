import React, {Component} from 'react';
import {Container, Row, Col, Card, Button, ButtonGroup, Form} from 'react-bootstrap';
import {Fetch} from "react-request";
import Composer from 'react-composer';
import config from 'react-global-configuration';
import Geocode from "react-geocode";
import GoogleMapReact from 'google-map-react';

class Dispatch extends Component {
    constructor(props) {
        super(props);
        this.toggleEditing = this.toggleEditing.bind(this);
        this.setDispatchData = this.setDispatchData.bind(this);
        this.handleChange = this.handleChange.bind(this);
        Geocode.setApiKey(config.get('maps_api_key'));
        this.state = {
            editing: false,
            dispatch_text: '',
            dispatch_loc: {},
        }

    }

    toggleEditing() {
        this.setState({...this.state, editing: !this.state.editing})
    }

    setDispatchData(data) {
        this.setState({...this.state, dispatch: data})
    }

    getDispatchText() {
        return {"dispatch_text": this.state.dispatch_text};
    }

    handleChange(event) {
        this.setState({dispatch_text: event.target.value});
    }

    renderMarkers(map, maps, title) {
        let marker = new maps.Marker({
            position: this.state.dispatch_loc,
            map,
            title: title
        });
    }

    render() {
        this.base_url = config.get('api_base_url');
        this.api_fetch_url = this.base_url + '/uid/' + this.props.match.params.uid;
        this.api_post_url = this.base_url + '/uid/' + this.props.match.params.uid;
        return (
            <div>
                <Container>
                    <br/>
                    <Row>
                        <Col><h2>Dispatch {this.props.match.params.uid}</h2></Col>
                    </Row>
                    <br/>
                    <Row>
                        <Composer
                            components={[
                                <Fetch url={this.api_fetch_url}/>,
                                <Fetch url={this.api_post_url} method="PUT"
                                />,
                            ]}>
                            {([readDispatch, modifyDispatch]) => {
                                if (readDispatch.fetching) {
                                    return <div>Loading data...</div>;
                                }

                                if (readDispatch.failed) {
                                    return <div>The request did not succeed.</div>;
                                }

                                if (readDispatch.data) {
                                    Geocode.fromAddress(readDispatch.data.address).then(
                                        response => {
                                            this.setState({
                                                ...this.state,
                                                dispatch_loc: response.results[0].geometry.location
                                            });
                                        },
                                        error => {
                                            console.error(error);
                                        }
                                    );
                                    return (
                                        <div>
                                            <Row>
                                                <Col>
                                                    <Card>
                                                        <Card.Body>
                                                            <Card.Title>Dispatch Text</Card.Title>
                                                            {!this.state.editing ?
                                                                <div>
                                                                    <Card.Text>
                                                                        {readDispatch.data.dispatch_text}
                                                                    </Card.Text>
                                                                    <Button variant="warning"
                                                                            onClick={this.toggleEditing}>Edit</Button>
                                                                </div>
                                                                :

                                                                <div>
                                                                    <Form.Group controlId="dispatch.DispatchText">
                                                                        <Form.Control
                                                                            defaultValue={readDispatch.data.dispatch_text}
                                                                            as="textarea" rows="7"
                                                                            onChange={this.handleChange}/>
                                                                    </Form.Group>
                                                                    <ButtonGroup aria-label="Basic example">
                                                                        <Button disabled={readDispatch.fetching}
                                                                                variant="success"
                                                                                onClick={() =>
                                                                                    modifyDispatch.doFetch({
                                                                                        headers: {'Content-Type': 'application/json'},
                                                                                        body: JSON.stringify(this.getDispatchText())
                                                                                    }).then(afterFetchInfo => {
                                                                                        this.toggleEditing();
                                                                                        readDispatch.doFetch();
                                                                                        console.log("Sent: " + JSON.stringify(this.getDispatchText()));
                                                                                        console.log(
                                                                                            'The call to doFetch resolved. Received result:',
                                                                                            afterFetchInfo
                                                                                        );
                                                                                    })
                                                                                }>Save</Button>
                                                                        <Button variant="danger"
                                                                                onClick={this.toggleEditing}>Cancel</Button>
                                                                    </ButtonGroup>
                                                                </div>
                                                            }
                                                        </Card.Body>
                                                    </Card>
                                                    <br/>
                                                    <Card>
                                                        <Card.Body>
                                                            <Card.Title>Dispatch Location:</Card.Title>
                                                            <Card.Text>{readDispatch.data.address}</Card.Text>
                                                            <Card.Title>Dispatched:</Card.Title>
                                                            <Card.Text>{readDispatch.data.timestamp}</Card.Text>
                                                        </Card.Body>
                                                    </Card>
                                                    <br/>
                                                </Col>
                                                <Col>
                                                    <Card style={{width: '40rem'}}>
                                                        <div style={{height: '75vh', width: '100%'}}>
                                                            <GoogleMapReact
                                                                bootstrapURLKeys={{key: config.get('maps_api_key')}}
                                                                center={this.state.dispatch_loc}
                                                                defaultZoom={11}
                                                                onGoogleApiLoaded={({map, maps}) => this.renderMarkers(map, maps, readDispatch.data.address)}
                                                            >
                                                            </GoogleMapReact>
                                                        </div>
                                                    </Card>
                                                </Col>
                                            </Row>
                                        </div>
                                    )
                                }
                            }
                            }
                        </Composer>
                    </Row>
                </Container>
            </div>
        );
    }
}

export default Dispatch;
