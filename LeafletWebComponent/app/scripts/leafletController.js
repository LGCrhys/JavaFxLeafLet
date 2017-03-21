var app = angular.module('intelRef',
    [ 'leaflet-directive',
      'ngRoute',
    ]);

app
.controller("LeafletMapsController", function($scope,$rootScope,leafletData) {

    $scope.hostile = true;
    $scope.inconnu = true;
    $scope.ami = true;

    $scope.markers = [];  	

    angular.extend($scope, {
       center: {
	        lat: 48.4000000,
	        lng: -4.4833300,
	        zoom: 5
	    },
        events: {
            map: {
                enable: ['moveend', 'popupopen'],
                logic: 'emit'
            },
            marker: {
                enable: [],
                logic: 'emit'
            }
        },
        layers: {
            baselayers: {
                imagery: {
                    name: "Imagery",
                    type: "agsBase",
                    layer: "Imagery",
                    visible: false
                },
                streets: {
                  name: "Streets",
                    type: "agsBase",
                    layer: "Streets",
                    visible: false
                },
                national: {
                    name: "National Geographic",
                    type: "agsBase",
                    layer: "NationalGeographic",
                    visible: false
                }
            },
            overlays: {
                locs: {
                    name: 'Locs',
                    type: 'markercluster',
                    visible: true
                }
            }
        }
    });

    var legendControl = L.Control.extend({

  		options:{position: 'bottomleft'},

          onAdd: function (map) {
              var div = L.DomUtil.create('div', 'info legend'),
                  labels = [];


              labels.push('<i class="hostile active" ></i> Hostile');
              labels.push('<i class="inconnu active" ></i> Inconnu');
              labels.push('<i class="ami active" ></i> Ami');

              div.innerHTML = labels.join('<br>');

              $('.hostile', div).on('click', $scope.toggleClass);
              $('.inconnu', div).on('click', $scope.toggleClass);
              $('.ami', div).on('click', $scope.toggleClass);

              return div;
          }
      });

    $scope.toggleClass = function(e){
    	if(!$(e.target).hasClass('active')){
    		$(e.target).addClass("active");
    	}
    	else{
    		$(e.target).removeClass("active");
    	}
    };

    leafletData.getMap().then(function(map) {
      $scope.map = map;
    	map.addControl(new legendControl());
    });

    var addressPointsToMarkers = function(points) {
      var markers = [];
      points.map(function(ap) {
        markers.push({
          layer: 'locs',
  	      lat: parseFloat(ap.latitude),
  	      lng: parseFloat(ap.longitude),
  	      icon: {
	      	type: 'awesomeMarker',
            markerColor: (ap.hostility==="Hostile")?'red':((ap.hostility==="Ami")?'green':'orange')
          }
        });
      });
      return markers;
    };
    leafletData.getLayers().then(function(layers) {
        $scope.markerClusterGrp = layers.overlays.locs;
        var clusters            = $scope.markerClusterGrp.getLayers();
    });

    $scope.displayLocalisations = function(localisations){
      $scope.markers = addressPointsToMarkers(localisations);
      //force map refresh
      $scope.$apply();
    };

});

