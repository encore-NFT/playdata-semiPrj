import React from "react";
import { Row } from "react-bootstrap";

const KidNewsList = ({data}) => {
    const newsData = [
        {
            "kidsNewsUrl": "http://kid.chosun.com/site/data/html_dir/2022/01/07/2022010700085.html",
            "kidsNewsTitle": "시사 낱말 퍼즐",
            "kidsNewsDate": "2022-01-07 00:01:00",
            "kidsNewsImg": "http://kid.chosun.com/site/data/img_dir/2022/01/07/2022010700087_0.jpg",
            "kidsNewsSource": "어린이조선일보"
        },
        {
            "kidsNewsUrl": "http://kid.chosun.com/site/data/html_dir/2022/01/07/2022010700083.html",
            "kidsNewsTitle": "이번 주 미션!",
            "kidsNewsDate": "2022-01-07 00:01:00",
            "kidsNewsImg": "http://kid.chosun.com/site/data/img_dir/2022/01/07/2022010700085_0.jpg",
            "kidsNewsSource": "어린이조선일보"
        },
        {
            "kidsNewsUrl": "http://kid.chosun.com/site/data/html_dir/2022/01/04/2022010402186.html",
            "kidsNewsTitle": "[뉴스탐험대 중계 코너] 갯벌어로, 韓食, 임인년 마케팅, 마인크래프트",
            "kidsNewsDate": "2022-01-05 00:01:00",
            "kidsNewsImg": "http://kid.chosun.com/site/data/img_dir/2022/01/04/2022010402181_0.jpg",
            "kidsNewsSource": "어린이조선일보"
        },
        {
            "kidsNewsUrl": "https://kids.donga.com?ptype=article&no=20220104133154393141&psub=news&gbn=01",
            "kidsNewsTitle": "[오늘의 뉴스]세계 호랑이 20%, 서식지 파괴로 멸종위기",
            "kidsNewsDate": "2022-01-04 04:31:00",
            "kidsNewsImg": "http://kids.donga.com/www/data/article/202201/c39fb5d832782acd5423ea049109aaeb_1641270643_6784.jpg",
            "kidsNewsSource": "어린이동아"
        },
        {
            "kidsNewsUrl": "http://kid.chosun.com/site/data/html_dir/2022/01/02/2022010200847.html",
            "kidsNewsTitle": "새해 복이 왔어~흥",
            "kidsNewsDate": "2022-01-03 03:00:00",
            "kidsNewsImg": "http://kid.chosun.com/site/data/img_dir/2022/01/02/2022010200847_0.jpg",
            "kidsNewsSource": "어린이조선일보"
        },
        {
            "kidsNewsUrl": "http://kid.chosun.com/site/data/html_dir/2022/01/02/2022010200851.html",
            "kidsNewsTitle": "지도력·용맹함·열정까지 '으뜸' 2022년 새해, 호랑이처럼 달려볼까요?",
            "kidsNewsDate": "2022-01-03 03:00:00",
            "kidsNewsImg": "http://kid.chosun.com/site/data/img_dir/2022/01/02/2022010200851_0.jpg",
            "kidsNewsSource": "어린이조선일보"
        },
        {
            "kidsNewsUrl": "http://kid.chosun.com/site/data/html_dir/2022/01/02/2022010200860.html",
            "kidsNewsTitle": "1월 작품을 뽑고 나서",
            "kidsNewsDate": "2022-01-03 03:00:00",
            "kidsNewsImg": "",
            "kidsNewsSource": "어린이조선일보"
        },
        {
            "kidsNewsUrl": "https://kids.donga.com?ptype=article&no=20220102134904512902&psub=news&gbn=01",
            "kidsNewsTitle": "2022년은 ‘검은 호랑이의 해’ “어흥~! 용맹함 본받고 싶지 않니?”",
            "kidsNewsDate": "2022-01-02 22:49:00",
            "kidsNewsImg": "http://kids.donga.com/www/data/article/202201/caf04089153f356f3e925c4819be2704_1641098485_4124.jpg",
            "kidsNewsSource": "어린이동아"
        }
    ]

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