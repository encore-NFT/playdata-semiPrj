import { useEffect, useState } from "react";
import News from "./News";
import WC from "./WC";
import 'bootstrap/dist/css/bootstrap.min.css'; 
import './App.css'
import {ButtonGroup, ToggleButton} from "react-bootstrap";
import SearchIcon from '@material-ui/icons/Search';


function App() {

  const [wcUrl, setWcUrl] = useState("http://localhost/kid")
  const [newsUrl, setNewsUrl] = useState("");
  const [newsDate, setNewsDate] = useState("");
  const [keyWord, setKeyWord] = useState("");
  const [radioValue, setRadioValue] = useState('1');

  const radios = [
    { name: '어린이', value: '1' },
    { name: '일반', value: '2' },
  ];

  useEffect(() =>  {
    console.log(wcUrl);
  }, [wcUrl]);

  useEffect(() =>  {
    console.log(newsDate);
  }, [newsDate]);


  const kidUrl = () => {
    setWcUrl("http://localhost/kid")
  };

  const adultUrl = () => {
    setWcUrl("http://localhost/adult")
  };

  const onSubmit = (e) => {
    // submit을 할때 refresh를 막아줌
    e.preventDefault();
    setNewsUrl(wcUrl + newsDate + "/" + keyWord);
  }

  return (
    <div className="App">
      <div className="container">

        {/* title */}
        <h2 className="mt-5"><span id="word">Word</span> <span id="cloud">Cloud</span></h2>

        {/* tab */}
        <div className="mt-4">
          <ButtonGroup>
            {radios.map((radio, idx) => (
              <ToggleButton
                key={idx}
                id={`radio-${idx}`}
                type="radio"
                variant={idx % 2 ? 'outline-warning' : 'outline-warning'}
                name="radio"
                value={radio.value}
                checked={radioValue === radio.value}
                onChange={(e) => setRadioValue(e.currentTarget.value)}
                onClick={idx === 1 ? adultUrl : kidUrl}
              >
                {radio.name}
              </ToggleButton>
            ))}
          </ButtonGroup>
        </div>
        
        {/* word cloud contents */}
        <div className="contentArea">
          <div className="m-2 pt-3">
            <input 
              type="date" 
              onChange={(e) => setNewsDate("/" + e.target.value)}/>
          </div>
          <div className="mt-3">
            <WC url={wcUrl + newsDate}/>
          </div>
        </div>
        
        {/* search box */}
        <form onSubmit={onSubmit}>
          <div className="searchBar">
            <SearchIcon/>
            <input
              className="searchBox"
              placeholder="클라우드 단어 검색" 
              value={keyWord}
              onChange={(e) => setKeyWord(e.target.value)}
            />
          </div>
        </form>

        {/* horizontal line */}
        <div className="mt-4">
        <hr className/>
        </div>

        <News url={newsUrl}/>
      </div>
    </div>
  );
}

export default App;
