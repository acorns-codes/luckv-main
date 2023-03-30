<template>
  <h2>참여내역</h2>
  <p>여기에 리스트 넣어야합니당</p>
  <v-table>
    <thead>
      <tr>
        <th v-for="(item, index) in headers" :key="index" class="text-center">
          {{ item.value }}
        </th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(item, index) in desserts" :key="index">
        <td>{{ index + 1 }}</td>
        <td>{{ item.name }}</td>
        <td>{{ item.content }}</td>
        <td>{{ item.payMax }}</td>
        <td>{{ item.bidding }}</td>
        <td>{{ item.status }}</td>
      </tr>
    </tbody>
  </v-table>
</template>

<script>
export default {
  props: ["attendList"],
  data() {
    return {
      headers: [
        { title: "index", value: "번호" },
        { title: "name", value: "제목" },
        { title: "content", value: "내용" },
        { title: "payMax", value: "최고가" },
        { title: "bidding", value: "입찰가" },
        { title: "status", value: "진행사항" },
      ],
      desserts: [
        {
          name: "Frozen Yogurt",
          content: 159,
          payMax: 6.0,
          bidding: 24,
          status: "1",
        },
        {
          name: "Jelly bean",
          content: 375,
          payMax: 0.0,
          bidding: 94,
          status: "0",
        },
        {
          name: "KitKat",
          content: 518,
          payMax: 26.0,
          bidding: 65,
          status: "6",
        },
        {
          name: "Eclair",
          content: 262,
          payMax: 16.0,
          bidding: 23,
          status: "7",
        },
        {
          name: "Gingerbread",
          content: 356,
          payMax: 16.0,
          bidding: 49,
          protein: 3.9,
          status: "16",
        },
        {
          name: "Ice cream sandwich",
          content: 237,
          payMax: 9.0,
          bidding: 37,
          status: "1",
        },
        {
          name: "Lollipop",
          content: 392,
          payMax: 0.2,
          bidding: 98,
          status: "2",
        },
        {
          name: "Cupcake",
          content: 305,
          payMax: 3.7,
          bidding: 67,
          protein: 4.3,
          status: "8",
        },
        {
          name: "Honeycomb",
          content: 408,
          payMax: 3.2,
          bidding: 87,
          status: "45",
        },
        {
          name: "Donut",
          content: 452,
          payMax: 25.0,
          bidding: 51,
          status: "22",
        },
      ],
    };
  },
};
</script>

<style></style>
