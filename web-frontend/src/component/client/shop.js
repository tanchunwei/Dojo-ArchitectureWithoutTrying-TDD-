import React, {useEffect, useState, useRef} from 'react';
import './../../App.css';
import {Link} from 'react-router-dom'
import {useAlert} from 'react-alert'

function Shop(){
    const alert = useAlert()
    const alertRef = useRef(alert);
    useEffect(() => {
        fetchItems()
    }, []);

    const [items, setItems]  = useState([]);

    const fetchItems = async (err) => {
        const data = await fetch('/api/getallinventory')
        .then((data) => {
            return data.json();
        })
        .catch(() => {
            alertRef.current.error("Error when getting inventory information");
        });

        if(data && data.result){
            setItems(data.response);
        }else{
            setItems([]);
        }
    }

    return (
        <div>
            <h3>Shop Page</h3>
            {items.map(item => (
                <Link key={item.barcode} to={`shop/${item.barcode}`}>
                    <h4>{item.name}</h4>
                </Link>
            ))}
        </div>
    );
}

export default Shop;