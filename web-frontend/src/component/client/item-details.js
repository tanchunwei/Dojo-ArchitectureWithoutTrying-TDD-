import React, {useEffect, useState} from 'react';
import './../../App.css';

function ItemDetails({ match }){

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

    return (
        <div>
            <h3>Item</h3>
            <h4>{item.name}</h4>
            <h4>{item.description}</h4>
            <h4>{item.displayPrice}</h4>
        </div>
    );
}

export default ItemDetails;