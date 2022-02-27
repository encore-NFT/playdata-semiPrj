// npm install react-d3-cloud
import WordCloud from 'react-d3-cloud';
import { scaleOrdinal } from 'd3-scale';
import { schemeCategory10 } from 'd3-scale-chromatic';

const data = [{"wordList":"있다","num":271},{"wordList":"하다","num":128},{"wordList":"우리","num":90},{"wordList":"없다","num":79},{"wordList":"미국","num":77},{"wordList":"이","num":75},{"wordList":"말","num":74},{"wordList":"람","num":69},{"wordList":"친구","num":69},{"wordList":"일","num":68},{"wordList":"받다","num":66},{"wordList":"환경","num":62},{"wordList":"같다","num":60},{"wordList":"어린이","num":60},{"wordList":"때","num":59},{"wordList":"좋다","num":50},{"wordList":"만들다","num":47},{"wordList":"많다","num":47},{"wordList":"나라","num":47},{"wordList":"시간","num":46},{"wordList":"최근","num":46},{"wordList":"활동","num":45},{"wordList":"생각","num":45},{"wordList":"세계","num":45},{"wordList":"장군","num":43},{"wordList":"연구","num":43},{"wordList":"되다","num":42},{"wordList":"탈레반","num":42},{"wordList":"아프가니스탄","num":40},{"wordList":"지구","num":40},{"wordList":"먹다","num":39},{"wordList":"나","num":38},{"wordList":"일본","num":36},{"wordList":"돈","num":35},{"wordList":"기업","num":35},{"wordList":"찾다","num":34},{"wordList":"올림픽","num":34},{"wordList":"들다","num":34},{"wordList":"이번","num":33},{"wordList":"로봇","num":33},{"wordList":"홍","num":33},{"wordList":"경제","num":33},{"wordList":"사람","num":32},{"wordList":"학교","num":31},{"wordList":"현지","num":31},{"wordList":"기온","num":31}];

const schemeCategory10ScaleOrdinal = scaleOrdinal(schemeCategory10);


function WC() {
    return (
        <div className='WordCloud'>
            <WordCloud
                data={(data.map(function(d) {
                    return {
                        text: d.wordList,
                        value: d.num
                    }
                }))}
                width={600}
                height={400}
                font="Helvetica"
                fontWeight="bold"
                fontSize={(word) => word.value * 0.5}
                spiral="rectangular"
                rotate={(word) => word.value % 360}
                padding={5}
                random={Math.random}
                fill={(d, i) => schemeCategory10ScaleOrdinal(i)}
                onWordClick={(event, d) => {
                    console.log(`onWordClick: ${d.text}`);
                }}
                onWordMouseOver={(event, d) => {
                    console.log(`onWordMouseOver: ${d.text}`);
                }}
                onWordMouseOut={(event, d) => {
                    console.log(`onWordMouseOut: ${d.text}`);
                }}
            />
        </div>
    );
}

export default WC;