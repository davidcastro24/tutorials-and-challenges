<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/userFormStyle.css"/>
</head>
<body>
    <form:form action="processForm" modelAttribute="person">
        <div class="row">
            <div class="col-25">
                <label for="fname">First Name</label>
            </div>
            <div class="col-75">
                <form:input type="text" id="fname" path="firstName" placeholder="Your name.."/>
                <form:errors path="firstName" cssClass="error"/>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="lname">Last Name</label>
            </div>
            <div class="col-75">
                <form:input type="text" id="lname" path = "lastName" placeholder="Your last name.."/>
                <form:errors path="lastName" cssClass="error"/>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="age">Age</label>
            </div>
            <div class="col-75">
                <form:input type="text" id="age" path = "age" placeholder="Your Age.."/>
                <form:errors path="age" cssClass="error"/>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="country">Country</label>
            </div>
            <div class="col-75">
                <form:select type="text" id="country" path = "country">
                    <form:options items ="${dataForDisplayedLists.countriesISO}"/>
                    <form:errors path="country" cssClass="error"/>
                </form:select>
            </div>
        </div>
        <div class="row">
                    <div class="col-25">
                        <label for="language">Language</label>
                    </div>
                    <div class="col-75">
                        <form:select type="text" id="language" path = "language">
                            <form:options items ="${dataForDisplayedLists.languages}"/>
                        </form:select>
                        <form:errors path="language" cssClass="error"/>
                    </div>
                </div>
        <div class="row">
                    <div class="col-25">
                        <label for="preferredOS">Preferred OS</label>
                    </div>
                    <div class="col-75" id="preferredOS">
                        Windows <form:checkbox path="preferredOS" value = "Windows"/>
                        Linux <form:checkbox path="preferredOS" value = "Linux"/>
                        Mac OS <form:checkbox path="preferredOS" value = "Mac OS"/>
                        <form:errors path="preferredOS" cssClass="error"/>
                    </div>
                </div>
        <div class="row">
            <div class="col-25">
                <label for="subject">Subject</label>
            </div>
            <div class="col-75">
                <form:input id="subject" path = "description" placeholder="Write something.."/>
                <form:errors path="description" cssClass="error"/>
            </div>
        </div>
        <div class="row">
            <input type="submit" value="Submit">
        </div>

    </form:form>
</div>
</body>
</html>
