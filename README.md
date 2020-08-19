# cis-msa-order
###  JDK-MissionControl Setting / VMOption : -Djava.rmi.server.hostname=127.0.0.1
###  Unresolved database references in annotations


##Success Flow
<ol>
    <li>Order Reg</li>
    <li>Order Reg event(order info(shopid,consumer id))</li>
    <li>Shop Validation</li>
    <li>Shop Validation Event(success,fail)</li>
    <li>Consumer Validation</li>
    <li>Consumer Validation Event( success,fail)</li>
    <li>Order Validation Complete</li>
    <li>Order Validation Complete Event</li>
    <li>Shop Ticket Create</li>    
 </ol>
 
##Fail Flow
<ol>
    <li>Order Reg</li>
    <li>Order Reg event(order info(shopid,consumer id))</li>
    <li>Shop Validation</li>
    <li>Shop Validation Event(success,fail)</li>
    <li>Consumer Validation</li>
    <li>Consumer Validation Event( success,fail)</li>
    <li>Order Validation error</li>
    <li>Order Reject</li>    
</ol>    