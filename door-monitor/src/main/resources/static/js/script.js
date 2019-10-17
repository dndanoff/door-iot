const element = (el, props = {}) => {
    var $el = $(document.createElement(el));
    $el.attr(props);
    return $el;
}

$( document ).ready(function() {
var jqxhr = $.get("http://iot.test.dreamix.eu/api/v1/doors", function(data) {
        console.log(data);
        if(!data || data.length === 0){
            $('#root').html('<h2 class="text-center">There is no data to show.</h2>');
            return;
        }
        for (let i in data) {
        $('#root .card-deck').append(
            element('div', {'class': 'card mb-4 shadow-sm'})
            .append(element('div', {'class': 'card-header'})
                .append(element('h4', {'class': 'card-title'}).text(data[i].name)))
            .append(element('div', {'class': 'card-body'})
                .append(element('h1').text(data[i].state.value===1?'CLOSED':'OPENED'))
                .append(element('img', {'class':'img-fluid status-image','src': data[i].state.value===1?'images/sad_poop.png':'images/happy_emoji.png'}))
                .append(element('p').text('Since '+data[i].state.createTime)))
            .append(element('div', {'class': 'card-footer'})
                .append(element('small', {'class': 'text-muted'}).text('Last updated: '+data[i].lastUpdated)))
        );
        };
    }).fail(function() {
        $('#root').html('<h2 class="text-center">There was problem with loading the data.</h2>');
    })
});