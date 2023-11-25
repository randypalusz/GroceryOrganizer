import { useState, useEffect } from 'react';
import { GroceryItem } from './GroceryItem.tsx'

function AllGroceries() {
    const [data, setData] = useState<Array<GroceryItem> | null>(null);

    function getAll() {
        const headers: Headers = new Headers()
        headers.set('Content-Type', 'application/json')
        headers.set('Accept', 'application/json')

        const request: RequestInfo = new Request('/api/getAll', {
            method: 'GET',
            headers: headers
        })

        fetch(request)
            .then(response => response.json())
            .then(json => setData(json as Array<GroceryItem>))
    }

    useEffect(() => {
        getAll();
    }, []);

    if (!data) {
        return <p>Loading...</p>
    }

    console.log(data)

    return (
        <div>
            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Location</th>
                    </tr>
                </thead>
                <tbody>
                    {data.map(item => (
                        <tr key={item.id}>
                            <td>{item.name}</td>
                            <td>{item.quantity}</td>
                            <td>{item.location}</td>
                        </tr>
                    ))}

                </tbody>
            </table>
        </div>
    )


}

export default AllGroceries