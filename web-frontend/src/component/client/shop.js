import React, {useEffect, useState} from 'react';
import './../../App.css';
import {Link} from 'react-router-dom'

function Shop(){

    useEffect(() => {
        fetchItems()
    }, []);

    const [items, setItems]  = useState([]);

    const fetchItems = async () => {
        const data = await fetch('/api/getallinventory')
        .then((data) => {
            return data.json();
        });

        console.log(data);

        if(data.result){
            setItems(data.response);
        }else{
            setItems([]);
        }
    }

    return (
        <div>
            <h3>Shop Page</h3>
            {items.map(item => (
                <Link to={`shop/${item.barcode}`}>
                    <h4>{item.name}</h4>
                </Link>
            ))}
        </div>
    );
}

export default Shop;