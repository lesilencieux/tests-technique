'use strict';

angular.module('myevent', [
    'angular-input-stars'
])
.factory('EventService', EventService)
.controller('EventsController', EventsController);

function EventService($http){
    return {
        deleteEvent:deleteEvent,
        getEvents:getEvents,
        updateStars: updateStars,
        searchEvents: searchEvents
    };

    function deleteEvent(id){
        return $http.delete('/api/events/' + id);
    }

    function getEvents(){
        return $http.get('/api/events/')
            .then(res => res.data);
    }

    function searchEvents(query){
        return $http.get(`/api/events/search/${ query }`)
            .then(res => res.data);
    }

    function updateStars(event){
        return $http.put('/api/events/' + event.id, event);
    }
}

function EventsController(EventService){
    var vm = this;
    vm.deleteEvent = deleteEvent;
    vm.updateStars = updateStars;
    vm.searchEvent = searchEvent;
    vm.query = '';

    activate();

    function activate() {
        return EventService.getEvents()
        .then(function(events) {
            vm.events = events;
            return vm.events;
        });
    }

    function searchEvent() {
        if(vm.query){
            return EventService.searchEvents(vm.query).then(events => {
                vm.events = events;
                return vm.events;
            });
        } else {
            activate();
        }
    }

    function deleteEvent(event){
        var index = vm.events.indexOf(event);
        return EventService.deleteEvent(event.id)
            .then(function() {
                vm.events.splice(index, 1);
            });
    }

    function updateStars(event){
        return EventService.updateStars(event);
    }
}