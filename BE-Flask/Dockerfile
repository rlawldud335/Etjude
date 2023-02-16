FROM tiangolo/uwsgi-nginx-flask:python3.7
WORKDIR /app
COPY . /app
RUN pip install -r requirements.txt
CMD python ./main.py