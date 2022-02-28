import React, { useEffect, useState } from "react";
import { Row } from "react-bootstrap";

const KidNewsList = ({data}) => {
    const [newsData, setNewsData] = useState([]);

    useEffect(() => {
        setNewsData(data);
        // console.log(newsData)
        return () => {
            console.log(data)
        }
    }, [newsData]);

    return (
        <div>
            {newsData.map(news => {
                return (
                    <div className="card mb-2" key={news.kidsNewsUrl}>
                        <div className="card-body p-3">
                            <Row>
                                <div className="col-sm-2">
                                    <img src={news.kidsNewsImg} alt="" className="imgThumb"/>
                                </div>
                                <div className="col-sm-10">
                                    <a href={news.kidsNewsUrl}>
                                        <span className="newsTitle">
                                            {(news.kidsNewsTitle).slice(0, 30)}
                                        </span>
                                    </a>
                                    <p className="newsInfo pt-1">
                                        {news.kidsNewsSource} | {news.kidsNewsDate}
                                    </p>
                                </div>
                            </Row>
                        </div>
                    </div>
                );
            })}
        </div>
    );
}

export default KidNewsList;