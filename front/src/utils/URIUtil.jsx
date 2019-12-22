var URIUtil = (function () {
    var mode; //TODO: в зависимости от места где запускается приложение (develop, test, prod, etc) с помощью этого флага менять uri
    var developEndpoint = "http://localhost:8001/";
    var getSiteURI = function (part) {
        return developEndpoint + part;
    };
    return {
        getSiteURI: getSiteURI
    }
})();

export default URIUtil;