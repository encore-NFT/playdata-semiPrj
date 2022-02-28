import React, { useEffect, useState } from "react";
import axios from "axios";
import WordCloud from "react-d3-cloud";
import { scaleOrdinal } from 'd3-scale';
import { schemeCategory10 } from 'd3-scale-chromatic';

const schemeCategory10ScaleOrdinal = scaleOrdinal(schemeCategory10);

function WC({url}) {
    const [data, setData] = useState([]);
    
    useEffect(() => {
        axios.get(url)
            .then(response => {
                setData(response.data.data);
            });
    }, [url]);

    return (
        <WordCloud
            data={(data.map(function(d) {
                return {
                    text: d.kidCountWord,
                    value: d.kidCountValue
                }
            }))}
            width={600}
            height={400}
            font="Helvetica"
            fontWeight="bold"
            fontSize={(word) => word.value}
            spiral="rectangular"
            rotate={(word) => word.value % 360}
            padding={5}
            random={Math.random}
            fill={(d, i) => schemeCategory10ScaleOrdinal(i)}
            onWordClick={(event, d) => {
                console.log(`onWordClick: ${d.text}`);
            }}
        />
    );
}

export default React.memo(WC);