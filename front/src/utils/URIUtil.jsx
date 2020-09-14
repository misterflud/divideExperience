var URIUtil = (function () {
    var mode; //TODO: в зависимости от места где запускается приложение (develop, test, prod, etc) с помощью этого флага менять uri
    var localEndpoint = "http://localhost:6003";
    // var developEndpoint = "http://192.168.100.56:6003";
    var developEndpoint = "";
    var delim = "/";
    var getSiteURI = function (part) {
        if (part.charAt(0) === delim) {
            return developEndpoint + part;
        }
        return developEndpoint + delim + part;
    };
    return {
        getSiteURI: getSiteURI
    }
})();

export default URIUtil;