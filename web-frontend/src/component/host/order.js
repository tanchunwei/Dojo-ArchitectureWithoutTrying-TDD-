import React from 'react';
import './../../App.css';
import {Timeline, TimelineEvent} from  'react-event-timeline'

function Order(){
    return (
        <div>
            <h3>Order Page</h3>

            <Timeline>
                <TimelineEvent
                    title="ABC"
                    createdAt="2020-05-12 11:05 PM"
                    icon={<i className="material-icons md-18">textsms</i>}>
                    I received the payment
                </TimelineEvent>

                {false ? (
                <TimelineEvent
                    title="ABC"
                    createdAt="2020-05-12 11:06 PM"
                    icon={<i className="material-icons md-18">textsms</i>}>
                    I received the payment
                </TimelineEvent>
                ) : <div></div>}

                <TimelineEvent
                    title="ABC"
                    createdAt="2020-05-12 11:07 PM"
                    icon={<i className="material-icons md-18">textsms</i>}>
                    I received the payment
                </TimelineEvent>
            </Timeline>
        </div>
    );
}

export default Order;