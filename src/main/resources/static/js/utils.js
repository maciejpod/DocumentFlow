/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function reloadStepWizard() {
    var nodes = $('.col-xs-1.bs-wizard-step');
    var cnt = nodes.length;
    var className = 'col-xs-1 bs-wizard-step ';
    className = className.replace(new RegExp("[0-9]{1,}", "g"), Math.ceil(12 / nodes.length));
    var status = 'complete';
    var i;
    for (i = 0; i < cnt; i++) {
        if ($(nodes[i]).first().text().trim() === $('#just-for-testing').text()) {
            $(nodes[i]).removeClass().addClass(className + 'active');
            status = 'disabled';
        } else if($('#just-for-testing').text() !== "") {
            $(nodes[i]).removeClass().addClass(className + status);
        } else {
            $(nodes[i]).removeClass().addClass(className + 'disabled');
        }
    }
}

function activateMenu(menuId) {
    $(menuId).click();
}

function setActive(menu, index) {
    var children = $(menu).children("li");
    $(children[index]).removeClass().addClass("active");
}

