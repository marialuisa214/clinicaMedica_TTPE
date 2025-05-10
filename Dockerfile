FROM python:3.10-slim

WORKDIR /app 

COPY requirements.txt /app/requirements.txt

RUN pip install -r requirements.txt

COPY main /app/main

EXPOSE 5000

CMD ["python", "./main/server/server.py"]
