FROM kkarczmarczyk/node-yarn
COPY . /home
WORKDIR /home/
RUN yarn install
ENTRYPOINT ["node","server.js"]
