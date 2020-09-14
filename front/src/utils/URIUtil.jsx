
const { REACT_APP_SERVER_ENV } = process.env;
console.log(`Version ${REACT_APP_SERVER_ENV}`);

var URIUtil = (function () {
    var mode; //TODO: в зависимости от места где запускается приложение (develop, test, prod, etc) с помощью этого флага менять uri
    var localEndpoint = "http://localhost:6003";
    var developEndpoint = "http://192.168.100.56:6003";
    var endpoint = "";
    var delim = "/";
    var api_suf = "api";
    var getSiteURI = function (part) {
        if (REACT_APP_SERVER_ENV == "local") {
            endpoint = localEndpoint;
        } else if (REACT_APP_SERVER_ENV == "dev") {
            endpoint = developEndpoint;
        }
        if (part.charAt(0) === delim) {
            return endpoint + part;
        }
        return endpoint + delim + part;
    };
    return {
        getSiteURI: getSiteURI
    }
})();

export default URIUtil;