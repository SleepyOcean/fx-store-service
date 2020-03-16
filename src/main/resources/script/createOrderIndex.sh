#!/bin/sh

#$1="http://192.168.88.128:9200"
#$2=shards=5
#$3=replicas=1
#$4=operation=create/delete

delIndex()
{
	curl -H "Content-Type: application/json" -XDELETE -i -s "$1/so_store_order_index" > /dev/null
	echo "delete index so_store_order_index successfully !"

}

createIndex()
{
  curl -H "Content-Type: application/json" -XPUT -i -s $1/so_store_order_index -d '
	{
	  "settings": {
		  "index.number_of_shards":'$2',
		  "index.number_of_replicas": '$3'
		},
	  "mappings":
	  {
		"order":{
			"dynamic":"strict",
			"_all":{
				"enabled":false
			},
			"_field_names":{
				"enabled":false
			},
			"properties":{
				"orderId":{
					"type":"keyword"
				},
				"userId":{
					"type":"keyword"
				},
				"goods":{
					"type":"text"
				},
				"goodsTotalPrice":{
					"type":"double"
				},
				"couponPrice":{
					"type":"double"
				},
				"deliveryPrice":{
					"type":"double"
				},
				"totalPrice":{
					"type":"double"
				},
				"contact":{
					"type":"text"
				},
				"contactName":{
					"type":"keyword"
				},
				"contactAddress":{
					"type":"text"
				},
				"orderTime":{
					"type":"date",
					"format":"yyyy-MM-dd HH:mm:ss"
				},
				"expectTime":{
					"type":"text"
				},
				"doneTime":{
					"type":"date",
					"format":"yyyy-MM-dd HH:mm:ss"
				},
				"payStatus":{
					"type":"integer"
				},
				"payWay":{
					"type":"integer"
				},
				"payTime":{
					"type":"date",
					"format":"yyyy-MM-dd HH:mm:ss"
				},
				"deliveryWay":{
					"type":"integer"
				},
				"deliveryStatus":{
					"type":"integer"
				},
				"deliveryManInfo":{
					"type":"text"
				},
				"status":{
					"type":"integer"
				},
				"comment":{
					"type":"text"
				}
			}
		}
	  }
	}'
	echo " create so_store_order_index successfully !"

	######################################################################################
	echo -e "\n\n=============================== _aliases ================================"
	curl -H "Content-Type: application/json" -XPOST -i $1/_aliases -d '
	{
		"actions": [
			{
				"add": {
					"index": "so_store_order_index",
					"alias": "so_store_order"
				}
			}
		]
	}' > /dev/null
}

echo $1
echo $2
echo $3
echo $4

if [ "$1" = "" ] ; then
	echo "please input es url, for example: http://172.16.138.40:9200";
	exit;
fi

if [ "$2" = "" ] ; then
	echo "please input shards number, for example: 5";
	exit;
fi

if [ "$3" = "" ] ; then
	echo "please input replicas number, for example: 1";
	exit;
fi

if [ "$4" = "" ] ; then
   echo "no input for operation type; auto run start!!";
   delIndex $1;
   createIndex $1 $2 $3;
elif [  "$4" = "create" ] ; then
   echo "create index start!!"
   createIndex $1 $2 $3;
elif [  "$4" = "delete" ] ; then
   echo "create index start!!"
   delIndex $1;
else
   echo "operation type must be create or delete!!"
fi
