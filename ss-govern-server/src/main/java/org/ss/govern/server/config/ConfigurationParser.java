package org.ss.govern.server.config;

import org.ss.govern.server.node.NodeInfo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author wangsz
 * @create 2020-04-11
 **/
public class ConfigurationParser {

    private ConfigurationParser() {
    }

    private static class Singleton {
        static ConfigurationParser instance = new ConfigurationParser();
    }

    public static ConfigurationParser getInstance() {
        return Singleton.instance;
    }

    public List<NodeInfo> parseMasterNodeServers() {
        GovernServerConfig config = GovernServerConfig.getInstance();
        String masterNodeServers = config.getMasterNodeServers();
        List<NodeInfo> nodeInfoList = new ArrayList<>();
        for (String masterNodeServer : masterNodeServers.split(";")) {
            String[] splitInfo = masterNodeServer.split(":");
            Integer nodeId = Integer.valueOf(splitInfo[0]);
            String ip = splitInfo[1];
            Integer masterConnectPort = Integer.valueOf(splitInfo[2]);
            Integer slaveConnectPort = Integer.valueOf(splitInfo[3]);
            Integer clientConnectPort = Integer.valueOf(splitInfo[4]);
            nodeInfoList.add(new NodeInfo(nodeId, ip, masterConnectPort, slaveConnectPort, clientConnectPort));
        }
        nodeInfoList.sort(Comparator.comparing(NodeInfo::getNodeId));
        return nodeInfoList;
    }
}
