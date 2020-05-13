import React, {useEffect, useState, useRef} from 'react';
import {useSelector} from 'react-redux'
import './../../App.css';
import {useAlert} from 'react-alert'

function ItemDetails({ match }){
    const alert = useAlert();
    const alertRef = useRef(alert);

    useEffect(() => {
        const fetchItem = async () => {
            const data = await fetch(`/api/barcode/${match.params.barcode}`)
            .then((data) => {
                return data.json();
            });

            if(data.result){
                setItem(data.response);
            }else{
                setItem({});
            }
        }
        fetchItem();
    }, [match]);

    const [item, setItem]  = useState({});
    const clientAuth = useSelector(state => state.clientAuth);

    const addCart = () => {
        fetch(`/api/addCart`,{
            method: 'post',
            headers:{
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                clientID : clientAuth.clientID,
                barcode : item.barcode
            })
        })
        .then((data) => {
            return data.json();
        })
        .then((res) =>{
            if(res.result){
                alertRef.current.info("Cart added successfully");
            }else{
                alertRef.current.info(res.message);
            }
        })
        .catch(() => {
            alertRef.current.error("Error when adding cart");
        });
    };

    return (
        <div>
            <h3>Item</h3>
            <h4>{item.name}</h4>
            <h4>{item.description}</h4>
            <h4>{item.displayPrice}</h4>
            <form onSubmit={addCart}>
                 <input type="button" value="add" onClick={addCart}/>
            </form>
        </div>
    );
}

export default ItemDetails;