export default function getRepsFromRegion(region) {
  switch(region) {
    case "Northeast":
      return [
        {
          name: "James Bond",
          firstName: "James" ,
          lastName: "Bond" ,
          id: "1234"
        },
        {
          name: "Kenn Martinez",
          firstName: "Kenn" ,
          lastName: "Martinez" ,
          id: "1111"
        },
        {
          name: "Sandra Bullock",
          firstName: "Sandra" ,
          lastName: "Bullock" ,
          id: "3333"
        }
      ]
    break;
    case "Southeast":
      return [
        {
          name: "Matt Damon",
          firstName: "Matt" ,
          lastName: "Damon" ,
          id: "2321"
        },
        {
          name: "Jennifer Lawrence",
          firstName: "Jennifer" ,
          lastName: "Lawrence" ,
          id: "1211"
        },
        {
          name: "Matthew McConaughey",
          firstName: "Matthew" ,
          lastName: "McConaughey" ,
          id: "1223"
        }
      ]
    break;
    case "Midwest":
      return [
        {
          name: "Chuck Norris",
          firstName: "Chuck" ,
          lastName: "Norris" ,
          id: "9999"
        },
        {
          name: "Carrie Underwood",
          firstName: "Carrie" ,
          lastName: "Underwood" ,
          id: "4444"
        },
        {
          name: "Logan Paul",
          firstName: "" ,
          lastName: "" ,
          id: "6666"
        }
      ]
      break;
  }
}
