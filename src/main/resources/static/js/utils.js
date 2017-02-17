/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function reloadStepWizard() {
    console.log('dziala');
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
        } else {
            $(nodes[i]).removeClass().addClass(className + status);
        }
    }
}

function activateMenu(menuId) {
    console.log("hello darkness my old friend"); 
    console.log(menuId);
    $(menuId).click();
}

function setActive(menu, index) {
    var children = $(menu).children("li");
    $(children[index]).removeClass().addClass("active");
}

