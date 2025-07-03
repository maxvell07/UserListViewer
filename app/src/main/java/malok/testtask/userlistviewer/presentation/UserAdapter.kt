package malok.testtask.userlistviewer.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import malok.testtask.userlistviewer.R
import malok.testtask.userlistviewer.data.UserListItemUi

class UserAdapter(
    private var users: List<UserListItemUi> = emptyList(),
    private val onItemClick: ((String) -> Unit)? = null
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatarImageView: ImageView = itemView.findViewById(R.id.imageViewAvatar)
        val fullNameTextView: TextView = itemView.findViewById(R.id.textViewName)
        val addressTextView: TextView = itemView.findViewById(R.id.textViewAddress)
        val phoneTextView: TextView = itemView.findViewById(R.id.textViewPhone)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val userId = users[position].id
                    onItemClick?.invoke(userId)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.fullNameTextView.text = user.fullName
        holder.phoneTextView.text = user.phone
        holder.addressTextView.text = user.adress
        Picasso.get()
            .load(user.avatarUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.avatarImageView)
    }

    override fun getItemCount(): Int = users.size

    fun setUsers(newUsers: List<UserListItemUi>) {
        users = newUsers
        notifyDataSetChanged()
    }

}
