package org.nethergames.proxytransport;

import dev.waterdog.waterdogpe.plugin.Plugin;
import org.nethergames.proxytransport.impl.event.DefaultTransportEventAdapter;
import org.nethergames.proxytransport.impl.event.TransportEventAdapter;
import org.nethergames.proxytransport.integration.CustomTransportServerInfo;

public class ProxyTransport extends Plugin {
    private static TransportEventAdapter eventAdapter;

    @Override
    public void onEnable() {
        getProxy().getServerInfoMap().removeServerInfoType(CustomTransportServerInfo.TYPE);
        getProxy().getServerInfoMap().registerServerInfoFactory(CustomTransportServerInfo.TYPE, CustomTransportServerInfo::new);

        getProxy().getServers().forEach((server) -> {
            getProxy().removeServerInfo(server.getServerName());
            getProxy().registerServerInfo(new CustomTransportServerInfo(server.getServerName(), server.getAddress(), server.getPublicAddress()));
        });

        setEventAdapter(new DefaultTransportEventAdapter());
    }

    public static TransportEventAdapter getEventAdapter() {
        return eventAdapter;
    }

    public static void setEventAdapter(TransportEventAdapter eventAdapter) {
        ProxyTransport.eventAdapter = eventAdapter;
    }
}
