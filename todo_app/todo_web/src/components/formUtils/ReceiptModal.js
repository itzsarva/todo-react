import React from 'react';
import {Modal} from 'react-bootstrap';
import {Col, Row, Container} from 'react-bootstrap';
import Divider from '@material-ui/core/Divider';
import Button from './Button';

export default function ReceiptModal({showModal, closeModal, receipt}) {
console.log('Coming here');
return(
    <Modal show={showModal} onHide={closeModal} backdrop="static">
        <Modal.Header className="modalHeader">Confirmation Receipt</Modal.Header>
        <Modal.Body className="modalBody" style={{fontFamily: 'monospace',background: '#343a40', color: 'white'}}>
        <div>
            <div style={{border: '1px solid white'}}>
                <Row>
                <Col>
                    <label >Persons Details</label>
                 </Col>
                </Row>
                <Row>
                    <Col sm={3}md={3} lg={3}>
                        <label >Name: </label>
                     </Col>
                     <Col sm={3}md={3} lg={3}>
                         <label >{receipt && receipt.name}</label>
                      </Col>
                </Row>
                <Row>
                    <Col sm={3}md={3} lg={3}>
                        <label >Contact: </label>
                     </Col>
                     <Col sm={3}md={3} lg={3}>
                         <label >{receipt && receipt.contact}</label>
                      </Col>
                </Row>
            </div>
            <div style={{border: '1px solid white', marginTop: '2px'}}>
                <Row>
                <Col>
                    <label >Car Details</label>
                 </Col>
                </Row>
                <Row>
                    <Col sm={3}md={3} lg={3}>
                        <label >Car: </label>
                     </Col>
                     <Col sm={3}md={3} lg={3}>
                         <label >{receipt && receipt.carName}</label>
                      </Col>
                </Row>
                <Row>
                    <Col sm={3}md={3} lg={3}>
                        <label >Doorstep Delivery: </label>
                     </Col>
                     <Col sm={3}md={3} lg={3}>
                         <label >{receipt && receipt.doorDelivery}</label>
                      </Col>
                </Row>
                <Row>
                    <Col sm={3}md={3} lg={3}>
                        <label >Pick-up Date/Time: </label>
                     </Col>
                     <Col sm={5}md={5} lg={5}>
                         <label >{receipt && receipt.pickUpDateWithTime}</label>
                      </Col>
                </Row>
                <Row>
                    <Col sm={3}md={3} lg={3}>
                        <label >Drop-off Date/Time: </label>
                     </Col>
                     <Col sm={5}md={5} lg={5}>
                         <label >{receipt && receipt.dropOffDateWithTime}</label>
                      </Col>
                </Row>
            </div>
            <div style={{border: '1px solid white', marginTop: '2px'}}>
                <Row>
                <Col>
                    <label >Payment Details</label>
                 </Col>
                </Row>
                <Row>
                    <Col sm={3}md={3} lg={3}>
                        <label >Amount: </label>
                     </Col>
                     <Col sm={3}md={3} lg={3}>
                         <label >{receipt && receipt.amount}</label>
                      </Col>
                </Row>
                <Row>
                    <Col sm={3}md={3} lg={3}>
                        <label >Excess: </label>
                     </Col>
                     <Col sm={3}md={3} lg={3}>
                         <label >{receipt && receipt.excessAmount}</label>
                      </Col>
                </Row>
            </div>
        </div>
        </Modal.Body>
        <Modal.Footer className="modalFooter">
        <Button action = {closeModal} type = {'secondary'} title = {'Close'} style={buttonStyle}/>
        </Modal.Footer>
    </Modal>
);
}

const buttonStyle = {
  margin : '10px 10px 10px 10px'
}