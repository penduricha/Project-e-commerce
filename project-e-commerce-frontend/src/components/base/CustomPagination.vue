<script>
export default {
  name: 'CustomPagination',
  components:{

  },
  data(){
    return{

    }
  },
  methods: {

  }
}
</script>

<template>
  <div style="justify-content: center; align-items: center; display: flex;">
    <nav aria-label="Page navigation example" style="text-align: center; height: 100%">
      <ul class="pagination">
        <li class="page-item">
          <a class="page-link link-item" href="#" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
          </a>
        </li>
        <li class="page-item "><a class="page-link link-item" href="#">1</a></li>
        <li class="page-item "><a class="page-link link-item" href="#">2</a></li>
        <li class="page-item "><a class="page-link link-item" href="#">3</a></li>
        <li class="page-item "><a class="page-link link-item" href="#">4</a></li>
        <li class="page-item ">
          <a class="page-link link-item" href="#" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
          </a>
        </li>
      </ul>
    </nav>
  </div>
</template>

<style>
.link-item{
  color: #DB4444;
  font-weight: 500;
}
</style>