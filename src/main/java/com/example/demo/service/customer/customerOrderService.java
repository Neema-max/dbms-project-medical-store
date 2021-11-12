package com.example.demo.service.customer;


import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.items;
import com.example.demo.model.order;
import com.example.demo.model.orderToItems;
import com.example.demo.model.itemShow.orderItemShow;
import com.example.demo.model.orderShow;
import com.example.demo.repository.authRepo;
import com.example.demo.repository.customer.customerOrderRepo;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
@Service
public class customerOrderService {
    @Autowired
    private customerOrderRepo cor;
    @Autowired
    private authRepo auth;
    public void saveOrder(order o){
        cor.saveOrder(o);
    }
    public void saveoti(orderToItems oti){
        cor.saveoti(oti);
    }
    public List<orderShow> getAllOrders(int cid){
        List<orderShow> ll = new ArrayList<orderShow>();
        List<order> orders = cor.getOrders(cid);
        for(int i=0;i<orders.size();i++){
            order o = orders.get(i);
            orderShow os = new orderShow();
            List<orderToItems> items = cor.getItemsByOrderId(o.getOrderId());
            List<orderItemShow> ot = new ArrayList<orderItemShow>();
            os.setOrder(o);
            for(int j=0;j<items.size();j++){
                orderToItems oti = items.get(j);
                int units = oti.getUnits();
                int itemId = oti.getItemId();
                orderItemShow ois = new orderItemShow();
                items item = auth.getItem(itemId);
                ois.setCategory(auth.getCatById(item.getCategoryId()));
                ois.setItem(item);
                ois.setItemImage(auth.getItemImage(itemId));
                ois.setUnits(units);
				ois.setPrice(oti.getPrice());
                ot.add(ois);
            }
            os.setOrderItems(ot);
            ll.add(os);
        }
        return ll;
    }
	public void updateItemQuantity(int units,int itemId){
		cor.updateItemQuantity(units , itemId);
	}
}




/*
#include<bits/stdc++.h>
using namespace std;

struct Bucket
{
	int ld=0;
	vector<int>a;
};
map<int,Bucket *>bmap;
int gd,bucket_capacity;
void display()
{
	for(int i=0;i<(1<<gd);i++)
	{
		cout<<"Local depth of the bucket is : "<<bmap[i]->ld<<"\n";
		for(auto j : bmap[i]->a)
			cout<<j<<" ";
		cout<<"\n";
	}
}

int search(int n)
{
	int h = n & ((1<<gd) - 1);
	return find((bmap[h]->a).begin(),(bmap[h]->a).end(),n)!=(bmap[h]->a).end();
}

void split(int sp_Index,Bucket * sp_Bucket)
{
	Bucket * newB = new Bucket;
	vector<int>temp;
	for(auto i : sp_Bucket->a)
	{
		temp.push_back(i);
	}
	
	sp_Bucket->a.clear();
	
	if(sp_Bucket->ld == gd)
	{
		bmap[sp_Index ^ (1<<gd)] = newB;
		if(gd!=0)
		{
			for(int i=0;i<(1<<gd);i++)
			{
				if(i!=sp_Index)
					bmap[i ^ (1<<gd)] = bmap[i];
			}
		}
		gd++;
		newB->ld = ++ sp_Bucket->ld;
		for(auto i : temp)
		{
			int h = i & ((1<<gd) -1);
			bmap[h]->a.push_back(i);
		}
	}
	else
	{
		int k = sp_Index & (1<<(sp_Bucket->ld) - 1);
		vector<int>indices;
		for(int i=0;i<(1<<gd);i++)
		{
			int last = i & (1<<(sp_Bucket->ld)) - 1;
			if(last==k)
				indices.push_back(i);
		}
		newB->ld = ++sp_Bucket->ld;
		for(int i=indices.size()/2;i<indices.size();i++)
		{
			bmap[indices[i]] = newB;
		}

		for(auto i : temp)
		{
			int h = i & ((1<<gd) -1);
			bmap[h]->a.push_back(i);
		}
	}

}
void insert(int n)
{
	int h = n & ((1<<gd) - 1);
	if(bmap[h]->a.size() < bucket_capacity)
		bmap[h]->a.push_back(n);
	else
	{
		split(h,bmap[h]);
		insert(n);
	}
}

void shrink()
{
	set <Bucket *>buckets;
	for(auto i : bmap)
		buckets.insert(i.second);
	int count = 0;
	int sz = buckets.size();
	for(auto i : buckets)
		if ( (gd - (i->ld)) == 1)
			count++;
	if(count==sz)
		gd--;
}

void merge()
{
	for(int i = 0; i<(1<<(gd-1));i++)
	{
		int h = i & ( ( 1<< ((bmap[i]->ld)-1) ) - 1);
		Bucket *curr = bmap[i];
		Bucket *matched = bmap[ i ^ (1<<(bmap[i]->ld-1)) ];
		if(curr != matched)
		{
			int sz = curr->a.size() + matched->a.size();
			if(sz <= bucket_capacity)
			{
				copy((matched->a).begin(),(matched->a).end(),back_inserter(curr->a));
				vector<int>inds;
				for(auto i : bmap)
				{
					if(i.second==matched)
						inds.push_back(i.first);
				}
				delete matched;
				for(auto j : inds)
					bmap[j] = bmap[i];
				bmap[i]->ld --;
			}
		}
	}
}


void del(int n)
{
	if(search(n))
	{
		for(auto i : bmap)
		{
			auto it = find((i.second->a).begin(),(i.second->a).end(),n);
			if(it!=i.second->a.end())
			{
				i.second->a.erase(it);
				break;
			}
		}
		merge();
		shrink();

		cout<<"Number deleted successfully\n";
	}
	else{
		cout<<"Element not found : \n";
	}
}
void menu()
{
	int choice;
	int n;
	while(1)
	{
		cout<<"1. Insertion\n";
		cout<<"2. Deletion\n";
		cout<<"3. Display\n";
		cout<<"4. Exit\n";
		cout<<"Enter your choice : \n";
		cin>>choice;
		switch(choice)
		{
			case 1:
				cout<<"Enter the number to be inserted : \n";
				cin>>n;
				insert(n);
				cout<<"Number inserted\n";
				break;
			case 2:
				cout<<"Enter the number to be deleted : \n";
				cin>>n;
				del(n);
				break;
			case 3:
				display();
				break;
			case 4:
				exit(0);
		}
	}
}
int main()
{
	cout<<"Enter bucket capacity : \n";
	cin>>bucket_capacity;
	bmap[0] = new Bucket;
	menu();
}


*/