/**
 * Simple proxy that forwards requests from the ui to the api.

 * Author: Brendan Jones, bpj1651@rit.edu, GitHub: brendanjones44
 */

const axios = require('axios');
const express = require('express');
var request = require('request');

const app = express();
const port = process.env.PORT || 5000;

const apiUrl = "http://erpsales_api:8080";

// Forward all requests prefacing with /api to the api
app.use('/api', function(req, res) {
  var url = apiUrl + req.url;
  console.log("Got me a request!")
  req.pipe(request(url)).pipe(res);
});

app.listen(port, () => console.log(`Listening on port ${port}`));
