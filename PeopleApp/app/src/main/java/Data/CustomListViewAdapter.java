package Data;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.eier.peopleapp.R;
import java.util.ArrayList;
import Model.Person;

/**
 * Created by Eier on 2/9/2018.
 */

public class CustomListViewAdapter extends ArrayAdapter<Person> {
    private ArrayList<Person> dataList = new ArrayList<>();
    private int layoutResources;
    private Activity activity;

    public CustomListViewAdapter(Activity act, int resource, ArrayList<Person> data) {
        super(act, resource, data);
        activity = act;
        layoutResources = resource;
        dataList =  data;
        notifyDataSetChanged();
    }

    @Override
    public Person getItem(int pos){
        return dataList.get(pos);
    }

    @Override
    public int getPosition(@Nullable Person item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       View row = convertView;
       ViewHolder holder = null;

       if (row == null || (row.getTag() == null)){
           LayoutInflater inflater = LayoutInflater.from(activity);
           row = inflater.inflate(layoutResources, null);
           holder = new ViewHolder();

           holder.name = (TextView) row.findViewById(R.id.listvName);
           holder.age = (TextView) row.findViewById(R.id.listvAge);
           holder.adress = (TextView) row.findViewById(R.id.listvAdress);

           row.setTag(holder);

       }
       else {
           holder = (ViewHolder) row.getTag();
       }

       holder.person = getItem(position);
       holder.name.setText(holder.person.getPersonName());
       holder.age.setText(String.valueOf(holder.person.getPersonAge()));
       holder.adress.setText(holder.person.getPersAdress());

       return row;
    }

    public class ViewHolder{
        TextView name;
        TextView age;
        TextView adress;
        Person person;
    }
}
