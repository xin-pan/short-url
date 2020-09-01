function getUrlFromForm() {
    var url = f.v.value.replace(/^\s\s*/, '').replace(/\s\s*$/, '')
    if ((url.indexOf('http://') !== 0) && (url.indexOf('https://') !== 0)) {
        return 'http://' + url
    }
    return url
}

function isValidUrl(url) {
    // no time to implement
    return true;
}

function inputError(text) {
    f.v.style.borderColor = 'red'
    error.innerHTML = text
    f.shortened.value = ''
}

function inputDisable() {
    f.v.enabled = false;
    f.s.enabled = false;
}

function inputEnable() {
    f.v.enabled = true;
    f.s.enabled = true;
}

function clearError() {
    f.v.style.borderColor = '#444'
    error.innerHTML = '&nbsp;'
}

function submit(su) {
    var http = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP")
    http.onreadystatechange = function() {
        if (http.readyState == 4) {
            inputEnable()
            if (http.status == 200) {
                if (http.responseText !== '') {
                    error.innerHTML = '&nbsp;'
                        // place result in form and select text
                    // f.v.value = http.responseText
                    f.shortened.value = http.responseText
                    f.shortened.focus()
                    f.shortened.select()
                } else {
                    error.innerHTML = 'Please try again.'
                }
            } else if (http.status == 400 || http.status == 500) {
                inputError(http.responseText)
            } else {
                error.innerHTML = 'Please try again.'
            }
        }
    };
    http.open('POST', '/api/url/generateShortUrl', true)
    http.setRequestHeader("content-type", "application/json")
    const sendData = {url: su}
    let body = JSON.stringify(sendData);
    http.send(body)
    error.innerHTML = 'Processing...'
}

window.onload = function() {
    f.v.onfocus = function() {
        clearError()
    };
    f.v.onclick = function() {
        f.v.select()
    };
    f.v.onchange = function() {
        clearError()
    };
    f.onsubmit = function() {
        // do basic validation, server will still refuse invalid Urls
        var su = getUrlFromForm()
        if (!isValidUrl(su)) {
            inputError('Invalid URL!')
        } else {
            f.v.style.color = 'black'
            inputDisable();
            submit(su);
        }
        // returning 'false' cancels HTML basic submit behavior
        return false
    };
};