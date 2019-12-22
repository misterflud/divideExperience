var UserProfile = (function() {
    var firstName;
    var secondName;
    var thirdName;
    var nickName;
    var email;
    var authToken;
    var role;

    var authorizationProcess = function(token) {
        deleteUserFromSession();
        localStorage.setItem('authToken', token);
    };

    var deleteUserFromSession = function() {
        localStorage.removeItem('authToken');
    };

    var isAuthorized = function () {
        return localStorage.getItem('authToken') !== null;
    };


    var getName = function() {
        return localStorage.getItem('firstName');
    };

    var setFirstName = function(nameParam) {
        localStorage.setItem('userName', nameParam);
        firstName = nameParam;// Also set this in cookie/localStorage
    };

    var getNickName = function() {
        return nickName;
    };

    var setNickName = function(nickNameParam) {
        this.nickName = nickNameParam;
    };

    var getEmail = function() {
        return email;
    };

    var setEmail = function(emailParam) {
        this.email = emailParam;
    };


    return {
        authorizationProcess: authorizationProcess,
        isAuthorized: isAuthorized,
        deleteUserFromSession: deleteUserFromSession,
    }

})();

export default UserProfile;