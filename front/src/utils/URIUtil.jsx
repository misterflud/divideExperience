var URIUtil = (function () {
    var mode; //TODO: в зависимости от места где запускается приложение (develop, test, prod, etc) с помощью этого флага менять uri
    var developEndpoint = "http://localhost:6002";
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