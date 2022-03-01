# 소년중앙 크롤링
import requests
import re
from bs4 import BeautifulSoup
from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
import time
import json, csv
# import os

# selenium options
options = webdriver.ChromeOptions()
options.headless = False
options.add_argument('window-size=1920x1080')
options.add_argument("disable-gpu")
options.add_argument("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36")
options.add_argument("lang=ko_KR")

# driver = webdriver.Chrome(executable_path=os.popen('which chromedriver').read().strip(),chrome_options=options)
# chrome_path = r'/opt/homebrew/bin/chromedriver' #path from 'which chromedriver'
# driver = webdriver.Chrome(executable_path=chrome_path, options=options)
driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()), options=options)

url = "https://sojoong.joins.com/archives/category/nie/issue"
req = requests.get(url)
driver.get(url)
time.sleep(1)

for i in range(4):
    driver.execute_script("window.scrollTo(0, document.body.scrollHeight)")
    driver.find_element_by_xpath("//tr[@class='btn_more']").click()
    # driver.find_element(by=By.XPATH, value="//tr[@class='btn_more']").click()
    time.sleep(1.5)

time.sleep(5)
# html = req.text
html = driver.page_source
soup = BeautifulSoup(html, "html.parser")

news_list = soup.select("h2 > a")
news_list = [p.get("href") for p in news_list]

data = []
for news in news_list:
    json_data = {}
    # sub crawling
    sub_req = requests.get(news)
    sub_html = sub_req.text
    sub_soup = BeautifulSoup(sub_html, "html.parser")

    # url: 기사 원문 URL
    json_data["news_url"] = news

    # title: 제목
    title = sub_soup.select_one("h1").text
    title = re.sub("[\r\n]", "", title).strip()
    json_data["news_title"] = title

    # subtitle: 부제목(어린이동아는 부제 없음!)
    # subtitle = sub_soup.select("div.hentry > p")[0]
    subtitle = ""
    json_data["news_subtitle"] = subtitle

    # writer: 기자
    # date: 날짜
    meta = sub_soup.select_one("div.post-meta").text.split("|")
    author = meta[0].strip()
    date = re.sub("\.", "-", meta[1]).strip()

    json_data["news_writer"] = author
    json_data["news_date"] = date

    # article: 기사 내용
    article = []
    p_list = sub_soup.select("p")
    end_point = None

    for i, p in enumerate(p_list):
        if p.get("class"):
            break
        text = re.sub("[\xa0\u200b\n\r\t]", "", p.text).strip()
        if text[-2:] == "일자":
            end_point = i + 1
        article.append(text)

    article = " ".join(article[:end_point])
    json_data["news_article"] = article

    # img_path: 기사 img 경로
    # img_path = sub_soup.select_one("figure").a.get("href")
    img_path = ""
    json_data["news_img"] = img_path

    # source: 신문사
    json_data["news_source"] = "소년중앙"

    if date >= "2021-09-01" :
        data.append(json_data)

for key in data[0]:
    print(f"{key}: {data[0][key][:50]}")

    # print (data)
 
def toJson(data):
    with open('/Users/imok/workspace/study/python/sojoong.json', 'w', encoding='utf-8') as json_file :
        json.dump(data, json_file, ensure_ascii=False, indent='\t')

# Json파일 생성
toJson(data)

