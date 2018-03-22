FROM kkarczmarczyk/node-yarn
COPY . /home
WORKDIR /home/
RUN yarn install
RUN yarn build
ENTRYPOINT ["yarn","start"]
