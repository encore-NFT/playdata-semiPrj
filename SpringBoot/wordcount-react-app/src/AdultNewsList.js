import React from "react";
import { Row } from "react-bootstrap";

const AdultNewsList = ({data}) => {
    return (
        <div>
            {data.map(news => {
                return (
                    <div className="card mb-2" key={news.adultNewsUrl}>
                        <div className="card-body p-3">
                            <Row>
                                <div className="col-sm-2">
                                    <img src={news.adultNewsImg} alt="..." className="imgThumb"/>
                                </div>
                                <div className="col-sm-10">
                                    <a href={news.adultNewsUrl}>
                                        <span className="newsTitle">
                                            {(news.adultNewsTitle).slice(0, 30)}
                                        </span>
                                    </a>
                                    <p className="newsInfo pt-1">
                                        {news.adultNewsSource} | {news.adultNewsDate}
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

export default AdultNewsList;