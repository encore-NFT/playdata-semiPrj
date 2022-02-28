import re
import requests
from datetime import datetime
from bs4 import BeautifulSoup
import json

# 접근정보 추가
headers = {'user-agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/15.3 Safari/605.1.15'}

def make_urllist(code, start_news_date, end_news_date):
    url = 'https://news.naver.com/main/list.naver?mode=LPOD&mid=sec&oid='+str(code)+'&date='+start_news_date+'&page=1'
    news = requests.get(url, headers=headers)
    news.content
    soup = BeautifulSoup(news.content, 'html.parser')
    start_news_num = soup.select('.type06_headline li dl')[0].a.get('href')[-10:]
    # print(start_news_num)

    url = 'https://news.naver.com/main/list.naver?mode=LPOD&mid=sec&oid='+str(code)+'&date='+end_news_date+'&page=1'
    news = requests.get(url, headers=headers)
    news.content
    soup = BeautifulSoup(news.content, 'html.parser')
    end_news_num = soup.select('.type06_headline li dl')[0].a.get('href')[-10:]
    # print(end_news_num)

    urllist= []
    for news_num in range(int(start_news_num)+1, int(end_news_num)):
        url = 'https://news.naver.com/main/read.naver?mode=LPOD&mid=sec&oid='+str(code)+'&aid='+str("{0:0>10}".format(news_num))
        urllist.append(url)

    return urllist

#언론사 뉴스 검색
#code =  “020”
start_news_date = "20210930"
end_news_date = "20211031"
news_urllist = []
for code in ['023','025','020','009']:
    urllist = make_urllist(code, start_news_date, end_news_date)
# code: 언론사 코드
    # 조선일보: 023
    # 중앙일보: 025
    # 동아일보: 020
    # 한겨례: 028
    # 매일경제: 009
# date: YYYYMMDD
    news_urllist.append(urllist)
    news_all_urllist = sum(news_urllist, [])

# print('뉴스 기사의 개수 :',len(news_all_urllist))
# print(news_all_urllist)

data = []
for news_url in news_all_urllist:
    json_data = {}

    # news_url: 기사 원문 URL
    json_data["news_url"] = news_url
    print(news_url)

    news_url = requests.get(news_url, headers=headers)
    news_url.content
    news_page_html = BeautifulSoup( news_url.content, 'html.parser')

    # news_title: 기사 제목
    try:
        if news_page_html.select_one('#articleTitle'):
            news_title = news_page_html.select_one('#articleTitle').text
        elif news_page_html.select_one('#title'):
            news_title = news_page_html.select_one('#title').text
    except:
        continue
    json_data["news_title"] = news_title
    print(news_title)

    # news_subtitle: 부제목
    try:
        if news_page_html.select_one(".media_end_summary"):
            news_subtitle = news_page_html.select_one(".media_end_summary").text
        else:
            news_subtitle = ""
    except:
        continue
    json_data["news_subtitle"] = news_subtitle
    print(news_subtitle)

    # news_wrtier: 기자
    try:
        if news_page_html.select_one(".journalistcard_summary_name"):
            news_wrtier = news_page_html.select_one(".journalistcard_summary_name").text
            news_wrtier = re.sub("[기자]","", news_wrtier).strip()
        elif news_page_html.select_one(".b_text"):
            news_wrtier = news_page_html.select_one(".b_text").text
            news_wrtier = re.sub("[|기자|인턴|특파원|\s|\d|a-z|(|@|.|)|]","", news_wrtier).strip()
        else:
            news_wrtier = ""
    except:
        continue
    json_data["news_wrtier"] = news_wrtier
    print(news_wrtier)

    # news_date: 날짜
    try:
        if news_page_html.select_one(".t11"):
            news_date = news_page_html.select_one(".t11").text
            news_date = re.sub("오전", "AM", news_date)
            news_date = re.sub("오후", "PM", news_date)
            news_date = datetime.strptime(news_date, "%Y.%m.%d. %p %I:%M").strftime("%Y-%m-%d %I:%M")
        else:
            news_date = ""
    except:
        continue

    json_data["news_date"] = news_date
    print(news_date)

    # news_article: 기사 내용
    try:
        if news_page_html.find('div', {'id':'articleBodyContents'}):
            news_article = news_page_html.find('div', {'id':'articleBodyContents'}).text.strip()
            news_article = news_article.replace("// flash 오류를 우회하기 위한 함수 추가", "")
            news_article = news_article.replace("function _flash_removeCallback() {}", "")
            ignore_article = "|\n|\t|\xa0|[WEEKLY|BIZ]|Biz|Calendar|[앵커]|[리포트]|이미지출처처=연합뉴스스]|[KBS|울산]|[포토]|[연합뉴스]|[경향신문]|"
            news_article = re.sub(ignore_article,"", news_article)
        else:
            news_article = ""
    except:
        continue
    json_data["news_article"] = news_article
    print(news_article)

    # news_img_path: 기사 img 경로
    # if news_soup.select("figure"):
    #     news_img_path = news_soup.select_one("figure").img.get("src")
    # else:
    #     news_img_path = "null"
    # json_data["news_img_path"] = news_img_path

    # news_source : 신문사
    try:
        if news_page_html.select_one(".c_text"):
            news_source = news_page_html.select_one(".c_text").get_text().strip()
            news_source = news_source.replace("Copyright ⓒ ", "")
            news_source = news_source.replace(". All rights reserved. 무단 전재 및 재배포 금지.", "")
        else:
            news_source = ""
    except:
        continue
    json_data["news_source"] = news_source
    print(news_source)

    data.append(json_data)
    print("------------------------------------------------------------------------------------")
# print(data)

# json 파일로 저장
def toJson(data):
    with open('/Users/mac/Downloads/Naver_News_2021-10.json', 'w', encoding='utf-8') as json_file :
        json.dump(data, json_file, ensure_ascii=False, indent='\t')

# Json파일 생성
toJson(data)