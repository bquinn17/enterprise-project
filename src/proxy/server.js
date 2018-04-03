/**
 * Simple proxy that forwards requests from the ui to the api.
 * Author: Brendan Jones, bpj1651@rit.edu
 */

const axios = require('axios');
const express = require('express');
var request = require('request');

const app = express();
const port = process.env.PORT || 5000;

const apiUrl = "http://erpsales_api:8080";
// app.get('/mocked/inventory/products', function(req, res) {
//   res.send(
//     [
//       {
//         "model": "Kenn-U-Style",
//         "quantity": "100",
//         "description": "The Kenn-U-Flex offers the ultimate balance of " +
//                        "comfort, usability, and durability. The actual " +
//                        "band around the watch is made from a superb and " +
//                        "proprietary material that was engineered for comfort."
//       },
//       {
//         "model": "Kenn-U-Active",
//         "quantity": "50",
//         "description": "The Kenn-U-Active is the perfect feature-packed " +
//                        "wearable watch for anyone looking to get more out " +
//                        "of their workout. Designed specifically for " +
//                        "athletes, this water-proof watch not only tracks and " +
//                        "logs your body's movements with military-grade " +
//                        "precision, but it also offers suggestions to better " +
//                        "your workouts."
//       },
//       {
//         "model": "Kenn-U-Flex",
//         "quantity": "20",
//         "description": "The Kenn-U-Style takes all the complex features of " +
//                        "a smartphone and packs them into a sleek and " +
//                        "fashionable watch. The remarkable interface allows " +
//                        "you access to all of your applications and " +
//                        "notifications, without sacrificing performance. " +
//                        "With processor speeds 2.3x faster than the industry " +
//                        "leading smartphone, this watch will make you want to " +
//                        "forget your cellphone even existed."
//       }
//     ]
//   )
// })
// Forward all requests prefacing with /api to the api
app.use('/api', function(req, res) {
  var url = apiUrl + req.url;
  req.pipe(request(url)).pipe(res);
});

app.listen(port, () => console.log(`Listening on port ${port}`));
