import React, { useEffect, useState } from "react";
import axios from "axios";
import KidNewsList from "./KidNewsList";
import AdultNewsList from "./AdultNewsList";

function News(props) {
    const url = props.url;
    const substring = "kid";

    const keyWord = props.keyWord;
    const [data, setData] = useState([]);

    useEffect((url) => {
        axios.get(url)
            .then(response => {
                setData(response.data.data);
            });
    }, []);
    
    if (url.includes(substring)) {
        return <KidNewsList data={data}/>;
    }
    return <AdultNewsList data={data}/>;
}

export default React.memo(News);