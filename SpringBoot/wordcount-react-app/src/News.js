import React, { useEffect, useState } from "react";
import axios from "axios";
import KidNewsList from "./KidNewsList";

function News({url}) {
    // const substring = "kid";
    const [data, setData] = useState([]);

    useEffect(() => {
        axios.get(url)
            .then(response => {
                setData(response.data.data);
                console.log(data)
            });
    }, [data]);
    
    return <KidNewsList data={data}/>;
}

export default React.memo(News);