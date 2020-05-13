import React, {useEffect, useRef} from 'react';
import {useSelector,useDispatch} from 'react-redux'
import './../../App.css';
import {useAlert} from 'react-alert'
import {Timeline, TimelineEvent} from  'react-event-timeline'
import {setCartAction} from './../../redux/actions/cartAction'

function Order(){
    const alert = useAlert();
    const alertRef = useRef(alert);
    const dispatch = useDispatch();
    const dispatchRef = useRef(dispatch);

    const cart = useSelector(state => state.cart);

    useEffect(() => {
        fetchItems()
    }, []);

    const fetchItems = async (err) => {
        const data = await fetch('/api/getallcart')
        .then((data) => {
            return data.json();
        })
        .catch(() => {
            alertRef.current.error("Error when getting cart information");
        });

        if(data && data.result){
            dispatchRef.current(setCartAction(data.response));
        }
    }

    return (
        <div>
            <h3>Order Page</h3>

            <Timeline>
                {cart.map(item => (
                    <TimelineEvent
                        title={item.clientID}
                        createdAt={item.displayDateTime}
                        icon={<i className="material-icons md-18">textsms</i>}>
                        Ordered {item.product.name} at {item.product.displayPrice}
                    </TimelineEvent>
                ))}
            </Timeline>
        </div>
    );
}

export default Order;