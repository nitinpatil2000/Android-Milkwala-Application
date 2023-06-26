package com.technosoul.milkwala.ui.masterinfo.route;

public class RouteFromServer {
    private int routeId;
    private String routeName;
    private String routeNo;
    private String routeDesc;
    private int deliveryPersonId;


    public RouteFromServer() {

    }

    public RouteFromServer(String routeName, String routeNo, String routeDesc) {
        this.routeName = routeName;
        this.routeNo = routeNo;
        this.routeDesc = routeDesc;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getRouteNo() {
        return routeNo;
    }

    public void setRouteNo(String routeNo) {
        this.routeNo = routeNo;
    }

    public String getRouteDesc() {
        return routeDesc;
    }

    public void setRouteDesc(String routeDesc) {
        this.routeDesc = routeDesc;
    }

    public int getDeliveryPersonId() {
        return deliveryPersonId;
    }

    public void setDeliveryPersonId(int deliveryPersonId) {
        this.deliveryPersonId = deliveryPersonId;
    }
}
