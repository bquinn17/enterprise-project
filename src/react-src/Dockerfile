FROM kkarczmarczyk/node-yarn
COPY . /home
WORKDIR /home/
RUN yarn install
ENV PATH /home/node_modules/.bin:$PATH

ENTRYPOINT ["yarn","start"]
