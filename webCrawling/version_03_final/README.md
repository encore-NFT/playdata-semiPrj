# Naver News Crawling

## Naver_news_crawling.py 실행하기

```zsh
# 원하는 크롤링 기간으로 *.py 파일의 코드를 편집 후 실행
% nohup python3 -u Naver_news_crawling_{뉴스 기간}.py &
```

## Back Ground 에서 실행중인 상태 확인

```zsh
% ps -ef|grep 'Naver_news.*'
```

## 입출력 로그 보기

```zsh
% tail -f nohup.out
```

## 프로세스 종료 시키기

~~~ zsh
# 프로세스ID 확인
% ps -ef|grep 'Naver.*'
# 프로세스 종료
% kill '프로세스ID'
~~~
